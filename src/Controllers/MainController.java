package Controllers;

import Business_Component.Entities.BusquedaYRegistro.Registro;
import Business_Component.Entities.EstudianteBL;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;
import Infraestructure_Component.RFIDReaderDevice;
import UserInterface_Component.MainFrame;
import UserInterface_Component.Panels.MonitorPanel;
import UserInterface_Component.Panels.RegistroPanel;
import UserInterface_Component.Panels.StartPanel;

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.io.File;
import java.util.Vector;

public class MainController {

    private final MainFrame mainFrame;
    private final StartPanel startPanel;
    private final MonitorPanel monitorPanel;
    private final RegistroPanel registroPanel;

    private RFIDReaderDevice rfidDevice;
    private EstudianteBL estudianteBL;
    private Registro registroBL;

    public MainController() {
        // Initialize View Components
        mainFrame = new MainFrame();
        startPanel = new StartPanel();
        monitorPanel = new MonitorPanel();
        registroPanel = new RegistroPanel();

        // Initialize Logic
        try {
            estudianteBL = new EstudianteBL();
            registroBL = new Registro();
        } catch (AppException e) {
            JOptionPane.showMessageDialog(null, "Error crítico iniciando lógica: " + e.getMessage());
            System.exit(1);
        }

        initStartPanel();
        initRegistroPanel();

        // Show GUI
        SwingUtilities.invokeLater(() -> {
            mainFrame.changePanel(startPanel);
            mainFrame.setVisible(true);
        });
    }

    private void initStartPanel() {
        refreshPorts();
        startPanel.addConnectListener(e -> attemptConnection());
    }

    private void initRegistroPanel() {
        registroPanel.addCancelarListener(e -> mainFrame.changePanel(startPanel)); // or monitor if connected
        registroPanel.addGuardarListener(e -> guardarNuevoEstudiante());
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
            startPanel.setStatus("Por favor, selecciona un puerto válido.", true);
            refreshPorts();
            return;
        }

        startPanel.setStatus("Conectando a " + selectedPort + "...", false);

        // Hardware Initialization
        rfidDevice = new RFIDReaderDevice(selectedPort, this::procesarLecturaTarjeta);

        new Thread(() -> {
            try {
                rfidDevice.start();
                SwingUtilities.invokeLater(() -> {
                    // Switch to Monitor Panel on success
                    mainFrame.changePanel(monitorPanel);
                });
            } catch (AppException e) {
                SwingUtilities.invokeLater(() -> startPanel.setStatus("Error: " + e.getMessage(), true));
            }
        }).start();
    }

    /**
     * Callback invocado por el hilo del Hardware cuando llega una tarjeta.
     */
    private void procesarLecturaTarjeta(String codigoTarjeta) {
        System.out.println("Tarjeta leida: " + codigoTarjeta); // Debug

        // Validar si estamos en modo registro (si la pantalla actual es registro)
        // Por simplicidad, si estamos en RegistroPanel, ignoramos lecturas nuevas o las
        // usamos para llenar el ID.

        try {
            EstudianteDTO estudiante = estudianteBL.validarAcceso(codigoTarjeta);

            SwingUtilities.invokeLater(() -> {
                if (estudiante != null) {
                    // 1. Mostrar INFO en Monitor
                    monitorPanel.mostrarEstudiante(estudiante);

                    // 2. Registrar Asistencia (CSV)
                    try {
                        registroBL.exportarDatosEstudiante(estudiante);
                        monitorPanel.mostrarMensaje("Asistencia registrada: " + estudiante.getNombre());
                    } catch (AppException e) {
                        monitorPanel.mostrarMensaje("Error guardando CSV: " + e.getMessage());
                    }

                    // Volver a Monitor si estábamos en otro lado
                    mainFrame.changePanel(monitorPanel);

                } else {
                    // Usuario NO existe -> Ir a Registro
                    int respuesta = JOptionPane.showConfirmDialog(mainFrame,
                            "La tarjeta [" + codigoTarjeta
                                    + "] no está registrada.\n¿Desea registrar un nuevo usuario?",
                            "Usuario Nuevo", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {
                        registroPanel.limpiarFormulario();
                        registroPanel.setCardId(codigoTarjeta);
                        mainFrame.changePanel(registroPanel);
                    } else {
                        monitorPanel.mostrarMensaje("Tarjeta desconocida: " + codigoTarjeta);
                        mainFrame.changePanel(monitorPanel);
                    }
                }
            });

        } catch (AppException e) {
            SwingUtilities
                    .invokeLater(() -> JOptionPane.showMessageDialog(mainFrame, "Error de Lógica: " + e.getMessage()));
        }
    }

    private void guardarNuevoEstudiante() {
        EstudianteDTO nuevo = registroPanel.obtenerDatos();

        if (nuevo.getNombre().isEmpty() || nuevo.getCedula().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Nombre y Cédula son obligatorios.");
            return;
        }

        try {
            if (estudianteBL.agregarEstudiante(nuevo)) {
                JOptionPane.showMessageDialog(mainFrame, "Estudiante registrado con éxito!");

                // Volver a Monitor
                mainFrame.changePanel(monitorPanel);
                monitorPanel.mostrarEstudiante(nuevo);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error al guardar en BD.");
            }
        } catch (AppException e) {
            JOptionPane.showMessageDialog(mainFrame, "Error crítico: " + e.getMessage());
        }
    }
}
