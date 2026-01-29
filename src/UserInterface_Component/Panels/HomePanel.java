package UserInterface_Component.Panels;

import Business_Component.Entities.EstudianteBL;
import Business_Component.Entities.BusquedaYRegistro.Busqueda;
import Business_Component.Entities.BusquedaYRegistro.Registro;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;
import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppMSG;
import Infraestructure_Component.Tools.AppResources;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomButton;
import UserInterface_Component.Components.CustomTextField;
import UserInterface_Component.Form.AttendanceTableConfig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePanel extends JPanel {

    /**
     * Hook para que el Controller navegue a RegistroPanel sin que HomePanel toque
     * MainFrame
     */
    public interface RegistroRequestHandler {
        void onRequestRegistro(String cardId);
    }

    private RegistroRequestHandler registroHandler;

    private final EstudianteBL      estudianteBL;
    private final Busqueda          busquedaBL;

    // Encabezado e info inferior
    private final JLabel            lblTitle = new JLabel("Sistema de Asistencia CheckLyn", AppUIConstants.ALIGN_CENTER);
    private final JLabel            lblStatus = new JLabel("Listo.", AppUIConstants.ALIGN_LEFT);
    
    // Botones
    private final CustomButton      btnExportarCSV = CustomButton.primary("Exportar a CSV");
    private final CustomButton      btnEliminarMode = CustomButton.danger("Eliminar Estudiante");
    
    // Elementos para borrar
    private final CustomTextField   txtCedula = new CustomTextField(18);
    private final CustomButton      btnBuscarCedula = CustomButton.primary("Buscar");
    private final CustomButton      btnEliminarConfirm = CustomButton.danger("Eliminar (Confirmar)");
    
    private final JLabel            lblInfoNombre = new JLabel("---");
    private final JLabel            lblInfoCedula = new JLabel("---");
    private final JLabel            lblInfoTarjeta = new JLabel("---");
    private final JLabel            lblInfoAula = new JLabel("---");
    private final JLabel            lblInfoEstado = new JLabel("---");

    private EstudianteDTO           estudianteEncontrado = null;

    // Tabla para visualizar registro de estudiantes en tiempo real
    private final DefaultTableModel attendanceModel;
    private final JTable            tblAttendance;

    // Getters para refactorizacion
    public JLabel getLblStatus() {
        return lblStatus;
    }
    public CustomButton getBtnExportarCSV() {
        return btnExportarCSV;
    }
    public CustomTextField getTxtCedula() {
        return txtCedula;
    }
    public CustomButton getBtnBuscarCedula() {
        return btnBuscarCedula;
    }
    public CustomButton getBtnEliminarConfirm() {
        return btnEliminarConfirm;
    }
    public JLabel getLblInfoNombre() {
        return lblInfoNombre;
    }
    public JLabel getLblInfoCedula() {
        return lblInfoCedula;
    }
    public JLabel getLblInfoTarjeta() {
        return lblInfoTarjeta;
    }
    public JLabel getLblInfoAula() {
        return lblInfoAula;
    }
    public JLabel getLblInfoEstado() {
        return lblInfoEstado;
    }
    public JTable getTblAttendance() {
        return tblAttendance;
    }
    
    private int maxRows = 300;

    public HomePanel() {
        // Business init (solo Business)
        try {
            this.estudianteBL = new EstudianteBL();
            this.busquedaBL = new Busqueda();
        } catch (AppException e) {
            throw new RuntimeException("Error inicializando Business en HomePanel: " + e.getMessage(), e);
        }

        setLayout       (new BorderLayout());
        setBackground   (AppColors.getBackground());
        setBorder       (AppUIConstants.emptyBorder(AppUIConstants.PADDING_L));

        // Header
        lblTitle.setFont                    (AppFonts.boldLarge().deriveFont(Font.BOLD, 22f));
        lblTitle.setForeground              (AppColors.getTextPrimary());
        lblTitle.setHorizontalAlignment     (SwingConstants.RIGHT);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_M));

        JLabel headerIcon   = buildHeaderIcon();
        JPanel leftIconWrap = new JPanel(new BorderLayout());
        leftIconWrap.setOpaque  (false);
        leftIconWrap.add        (headerIcon, BorderLayout.WEST);

        header.add(leftIconWrap, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main split
        JPanel left      = new LeftPanel(this);
        JPanel right     = new RightPanel(this);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);

        split.setResizeWeight(0.34);
        split.setDividerSize (8);
        split.setBorder      (null);
        split.setOpaque      (false);
        add(split, BorderLayout.CENTER);

        // Status bar
        lblStatus.setFont(AppFonts.normal());
        lblStatus.setForeground(AppColors.getTextSecondary());

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setOpaque(false);
        statusBar.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        // Table model
        attendanceModel = new DefaultTableModel(
                new Object[] { "Hora", "Cedula", "Nombre", "Aula", "Tarjeta", "Estado" },
                0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tblAttendance = new JTable(attendanceModel);
        AttendanceTableConfig.configureTable(tblAttendance);

        // Place table in right panel center
        AttendanceTableConfig.replaceTableInRightPanel(right, tblAttendance);

        // Default state
        btnEliminarConfirm.setEnabled(false);

        // Wire UI logic (Business only)
        initHomeLogic();
    }

    public void setRegistroRequestHandler(RegistroRequestHandler handler) {
        this.registroHandler = handler;
    }

    // =========================================================
    // LÓGICA HOME (Business ONLY)
    // =========================================================

    private void initHomeLogic() {
        // Botón para exportar la tabla de asistencias a CSV
        btnExportarCSV.addActionListener (e -> exportarTablaACSV());

        btnEliminarMode.addActionListener
        (e -> {
                  txtCedula.requestFocusInWindow();
                  txtCedula.selectAll();
                  setStatusMessage("Modo eliminación: ingresa cédula y presiona Buscar.");
              }
        );

        btnBuscarCedula.addActionListener
        (e -> {
                  String cedula = getCedulaInput();
                  if (cedula.isBlank()) {
                      AppMSG.showError("Ingresa una cédula para buscar.");
                      return;
                  }
              
                  try {
                      EstudianteDTO dto = busquedaBL.buscarPorCedula(cedula);
                      if (dto == null) {
                          estudianteEncontrado = null;
                          showEstudiante(null);
                          AppMSG.showError("No se encontró estudiante con esa cédula.");
                          setStatusMessage("Búsqueda sin resultados: " + cedula);
                      } else {
                          estudianteEncontrado = dto;
                          showEstudiante(dto);
                          setStatusMessage("Estudiante encontrado: " + dto.getNombre() + " " + dto.getApellido());
                      }
                  } catch (AppException ex) {
                      AppMSG.showError("Error buscando estudiante: " + ex.getMessage());
                  }
              }
        );

        btnEliminarConfirm.addActionListener
        (e -> {
                  if (estudianteEncontrado == null || estudianteEncontrado.getIdEstudiante() == null) {
                      AppMSG.showError("Primero busca un estudiante válido.");
                      return;
                  }
              
                  boolean ok = AppMSG.showConfirmYesNo(
                          "¿Seguro que deseas eliminar al estudiante?\n" +
                                  estudianteEncontrado.getNombre() + " " + estudianteEncontrado.getApellido() +
                                  " (Cédula: " + estudianteEncontrado.getCedula() + ")");
                  
                  if (!ok) {
                      setStatusMessage("Eliminación cancelada.");
                      return;
                  }
              
                  try {
                      boolean deleted = estudianteBL.eliminarEstudiante(estudianteEncontrado.getIdEstudiante());
                      if (deleted) {
                          AppMSG.showSuccess("Estudiante eliminado correctamente.");
                          setStatusMessage("Eliminado: " + estudianteEncontrado.getCedula());
                          estudianteEncontrado = null;
                          showEstudiante(null);
                      } else {
                          AppMSG.showError("No se pudo eliminar el estudiante.");
                      }
                  } catch (AppException ex) {
                      AppMSG.showError("Error eliminando: " + ex.getMessage());
                  }
              }
        );
    }

    private JLabel buildHeaderIcon() {
        int size = 48;
        try {

            ImageIcon icon  = new ImageIcon(AppResources.getImgIcon());
            Image scaled    = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            JLabel label    = new JLabel(new ImageIcon(scaled));

            label.setPreferredSize(new Dimension(size, size));
            return label;

        } catch (Exception e) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(size, size));
            return label;
        }
    }

    // =========================================================
    // UI Public helpers
    // =========================================================

    public String getCedulaInput() {
        return txtCedula.getText().trim();
    }

    public void setStatusMessage(String msg) {
        lblStatus.setText(msg == null ? "" : msg);
    }

    public void showEstudiante(EstudianteDTO e) {
        if (e == null) {
            lblInfoNombre       .setText("---");
            lblInfoCedula       .setText("---");
            lblInfoTarjeta      .setText("---");
            lblInfoAula         .setText("---");
            lblInfoEstado       .setText("---");
            lblInfoEstado       .setForeground(AppColors.getTextPrimary());
            btnEliminarConfirm  .setEnabled(false);
            return;
        }

        lblInfoNombre    .setText(nz(e.getNombre()) + " " + nz(e.getApellido()));
        lblInfoCedula    .setText(nz(e.getCedula()));
        lblInfoTarjeta   .setText(nz(e.getIdTarjeta()));
        lblInfoAula      .setText(String.valueOf(e.getAula()));
        lblInfoEstado    .setText(nz(e.getEstado()));

        if ("A".equalsIgnoreCase(e.getEstado())) {
            lblInfoEstado.setForeground(AppColors.getSuccess().darker());
        } else {
            lblInfoEstado.setForeground(AppColors.getError());
        }
        btnEliminarConfirm.setEnabled(true);
    }

    public void clearAttendanceTable() {
        attendanceModel.setRowCount(0);
    }

    public void appendAttendanceFromEstudiante(EstudianteDTO e) {
        if (e == null)
            return;

        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        attendanceModel.addRow(new Object[] {
                hora,
                nz(e.getCedula()),
                (nz(e.getNombre()) + " " + nz(e.getApellido())).trim(),
                String.valueOf(e.getAula()),
                nz(e.getIdTarjeta()),
                nz(e.getEstado())
        });

        trimRowsIfNeeded();
        scrollToBottom();
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = Math.max(50, maxRows);
    }

    // =========================================================
    // Helpers
    // =========================================================

    private void trimRowsIfNeeded() {
        while (attendanceModel.getRowCount() > maxRows) {
            attendanceModel.removeRow(0);
        }
    }

    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            int last = tblAttendance.getRowCount() - 1;
            if (last >= 0) {
                tblAttendance.scrollRectToVisible(tblAttendance.getCellRect(last, 0, true));
                tblAttendance.setRowSelectionInterval(last, last);
            }
        });
    }

    private String nz(String s) {
        return s == null ? "" : s;
    }

    // =========================================================
    // Exportar tabla de asistencias a CSV
    // =========================================================

    private void exportarTablaACSV() {
        // Validamos primero (Regla de negocio rápida)
        if (attendanceModel.getRowCount() == 0) {
            AppMSG.showWarning("No hay registros de asistencia para exportar.");
            return;
        }

        try {
            // Ejecutamos la lógica extraída
            File archivoGenerado = Registro.exportarTablaACSV(attendanceModel);

            // Notificamos éxito
            AppMSG.showSuccess("Archivo exportado correctamente:\n" + archivoGenerado.getName());
            setStatusMessage("CSV exportado en: " + archivoGenerado.getParent());

            // Intentamos abrir la carpeta automáticamente
            try {
                java.awt.Desktop.getDesktop().open(archivoGenerado.getParentFile());
            } catch (Exception ignored) { }

        } catch (IOException e) {
            AppMSG.showError("Error al exportar CSV: " + e.getMessage());
            setStatusMessage("Error en la exportación.");
        }
    }
}
