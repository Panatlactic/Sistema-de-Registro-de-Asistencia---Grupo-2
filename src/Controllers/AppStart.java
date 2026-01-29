package Controllers;

import Business_Component.Entities.EstudianteBL;
import Business_Component.Entities.LoginTutorBL;
import Business_Component.Entities.BusquedaYRegistro.Registro;
import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.DTOs.TutorDTO;
import Infraestructure_Component.AppException;
import Infraestructure_Component.RFIDReaderDevice;
import Infraestructure_Component.Tools.AppMSG;
import UserInterface_Component.MainFrame;
import UserInterface_Component.Panels.*;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.util.Vector;

public class AppStart {

    private final MainFrame mainFrame;
    private final LoginPanel loginPanel;
    private final StartPanel startPanel;
    private final HomePanel homePanel;
    private final RegistroPanel registroPanel;

    private RFIDReaderDevice rfidDevice;
    private String connectedPort;

    // Control para saber si estamos en HomePanel y podemos procesar tarjetas
    private volatile boolean rfidEnabled = false;

    // Business
    private EstudianteBL estudianteBL;
    private LoginTutorBL loginBL;
    //private Registro registroBL;

    // Tutor logueado
    private TutorDTO tutorLogueado;

    public AppStart() {
        mainFrame = new MainFrame();
        loginPanel = new LoginPanel();
        startPanel = new StartPanel();
        homePanel = new HomePanel();
        registroPanel = new RegistroPanel();

        try {
            estudianteBL = new EstudianteBL();
            loginBL = new LoginTutorBL();
            //registroBL = new Registro();
        } catch (AppException e) {
            AppMSG.showErrorCritico("Error crítico iniciando Business: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // Configurar navegación
        wireLoginPanel();
        wireStartPanel();
        wireHomePanel();
        wireRegistroPanel();

        // Iniciar en Login (después del Splash)
        SwingUtilities.invokeLater(() -> {
            showLogin();
            mainFrame.setVisible(true);
        });
    }

    // =========================================================
    // Navegación
    // =========================================================

    private void showLogin() {
        rfidEnabled = false; // Desactivar lectura RFID
        loginPanel.limpiarCampos();
        mainFrame.changePanel(loginPanel);
        loginPanel.enfocarUsuario();
    }

    private void showStart() {
        rfidEnabled = false; // Desactivar lectura RFID
        refreshPorts();
        mainFrame.changePanel(startPanel);
    }

    private void showHome() {
        System.out.println("[NAV] showHome() - Activando RFID...");
        mainFrame.changePanel(homePanel);
        rfidEnabled = true; // Activar lectura RFID
        System.out.println("[NAV] rfidEnabled = " + rfidEnabled);
    }

    private void showRegistro(String cardId) {
        System.out.println("[NAV] showRegistro() - Desactivando RFID...");
        rfidEnabled = false; // Desactivar lectura RFID mientras se registra
        registroPanel.limpiarFormulario();
        registroPanel.setCardId(cardId);
        mainFrame.changePanel(registroPanel);
        System.out.println("[NAV] rfidEnabled = " + rfidEnabled);
    }

    // =========================================================
    // LoginPanel: autenticación de tutor
    // =========================================================

    private void wireLoginPanel() {
        loginPanel.addIngresarListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String usuario = loginPanel.getUsuario();
        String clave = loginPanel.getClave();

        // Validaciones básicas
        if (usuario.isEmpty()) {
            loginPanel.mostrarError("Ingresa tu nombre de usuario.");
            return;
        }
        if (clave.isEmpty()) {
            loginPanel.mostrarError("Ingresa tu contraseña.");
            return;
        }

        loginPanel.limpiarError();

        try {
            TutorDTO tutor = loginBL.validarLogin(usuario, clave);

            if (tutor != null) {
                tutorLogueado = tutor;
                AppMSG.showSuccess("¡Bienvenido " + tutor.getNombreCompleto() + "!");
                showStart(); // Ir a configurar Arduino
            } else {
                loginPanel.mostrarError("Usuario o contraseña incorrectos.");
            }
        } catch (AppException ex) {
            loginPanel.mostrarError("Error del sistema. Intenta nuevamente.");
            System.err.println("Error en login: " + ex.getMessage());
        }
    }

    // =========================================================
    // StartPanel: conexión hardware
    // =========================================================

    private void wireStartPanel() {
        startPanel.addConnectListener(e -> attemptConnection());
    }

    private void refreshPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();
        Vector<String> portNames = new Vector<>();

        if (ports.length == 0) {
            portNames.add("No se detectaron puertos");
        } else {
            for (SerialPort port : ports) {
                portNames.add(port.getSystemPortName());
            }
        }
        startPanel.setPortList(portNames);
    }

    private void attemptConnection() {
        String selectedPort = startPanel.getSelectedPort();

        if (selectedPort == null || selectedPort.contains("No se detectaron")) {
            startPanel.setStatus("Selecciona un puerto válido.", true);
            refreshPorts();
            return;
        }

        startPanel.setStatus("Conectando a " + selectedPort + "...", false);

        connectedPort = selectedPort;
        rfidDevice = new RFIDReaderDevice(selectedPort, this::onCardReadFromHardware);

        new Thread(() -> {
            try {
                rfidDevice.start();
                SwingUtilities.invokeLater(() -> {
                    startPanel.setStatus("Conectado: " + connectedPort, false);

                    // showLogin();
                    showHome();
                });
            } catch (AppException ex) {
                SwingUtilities.invokeLater(() -> startPanel.setStatus("Error: " + ex.getMessage(), true));
            }
        }).start();
    }

    // =========================================================
    // RFID callback (Hardware → Controller → HomePanel)
    // =========================================================

    private void onCardReadFromHardware(String codigoTarjeta) {
        System.out.println("[RFID-CB] Tarjeta recibida: " + codigoTarjeta + " | rfidEnabled=" + rfidEnabled);
        
        // Solo procesar si estamos en HomePanel y RFID está habilitado
        if (!rfidEnabled) {
            System.out.println("[RFID-CB] Lectura ignorada (panel no activo)");
            return;
        }
        
        // Siempre vuelve al EDT para tocar UI
        SwingUtilities.invokeLater(() -> {
            System.out.println("[RFID-CB] En EDT, rfidEnabled=" + rfidEnabled);
            if (rfidEnabled) { // Doble verificación en EDT
                homePanel.onCardRead(codigoTarjeta);
            }
        });
    }

    // =========================================================
    // HomePanel: hooks de navegación (Registro)
    // =========================================================

    private void wireHomePanel() {
        homePanel.setRegistroRequestHandler(cardId -> showRegistro(cardId));
    }

    // =========================================================
    // RegistroPanel: guardar nuevo estudiante (Business)
    // =========================================================

    private void wireRegistroPanel() {
        registroPanel.addCancelarListener(e -> {
            System.out.println("[REGISTRO] Botón Cancelar presionado");
            showHome();
        });

        registroPanel.addGuardarListener(e -> {
            // Primero validar campos
            String errorValidacion = registroPanel.validarCampos();
            if (errorValidacion != null) {
                AppMSG.showError(errorValidacion);
                return;
            }

            EstudianteDTO nuevo = registroPanel.obtenerDatos();

            try {
                if (estudianteBL.agregarEstudiante(nuevo)) {
                    AppMSG.showSuccess("Estudiante registrado con éxito.");
                    showHome();
                    // Registrar asistencia del nuevo estudiante
                    homePanel.appendAttendanceFromEstudiante(nuevo);
                    homePanel.setStatusMessage("Nuevo estudiante registrado: " + nuevo.getNombre() + " " + nuevo.getApellido());
                } else {
                    AppMSG.showError("No se pudo guardar en BD. Verifica que la cédula o tarjeta no estén duplicadas.");
                }
            } catch (AppException ex) {
                AppMSG.showError("Error al registrar: " + ex.getMessage());
            }
        });
    }

   
    public void stopHardware() {
        if (rfidDevice != null)
            rfidDevice.stop();
    }
}
