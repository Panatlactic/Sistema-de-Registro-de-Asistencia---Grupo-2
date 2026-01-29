package UserInterface_Component.Panels;

import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomButton;
import UserInterface_Component.Components.CustomSecondPanel;
import UserInterface_Component.Components.CustomTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel de registro de nuevos estudiantes.
 * Diseño unificado con el resto de la aplicación.
 */
public class RegistroPanel extends JPanel {

    // ===== Campos del formulario =====
    private final JLabel lblIdTarjeta;
    private final CustomTextField txtNombre;
    private final CustomTextField txtApellido;
    private final CustomTextField txtCedula;
    private final CustomTextField txtEdad;
    private final JComboBox<String> cmbSexo;
    private final CustomTextField txtAula;

    // ===== Botones =====
    private final CustomButton btnGuardar;
    private final CustomButton btnCancelar;

    // Ancho estándar para campos de texto
    private static final int FIELD_WIDTH = 320;
    private static final int FIELD_HEIGHT = 40;

    public RegistroPanel() {
        setLayout(new GridBagLayout());
        setBackground(AppColors.getBackground());

        // ===== CONTENEDOR PRINCIPAL =====
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setOpaque(false);

        // ===== TÍTULO =====
        JLabel lblTitle = new JLabel("Registrar Nuevo Estudiante");
        lblTitle.setFont(AppFonts.boldLarge().deriveFont(Font.BOLD, 26f));
        lblTitle.setForeground(AppColors.getTextPrimary());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblTitle);
        mainContainer.add(Box.createVerticalStrut(8));

        JLabel lblSubtitle = new JLabel("Complete los datos del estudiante");
        lblSubtitle.setFont(AppFonts.small());
        lblSubtitle.setForeground(AppColors.getTextSecondary());
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblSubtitle);
        mainContainer.add(Box.createVerticalStrut(22));

        // ===== CARD DEL FORMULARIO =====
        CustomSecondPanel cardForm = new CustomSecondPanel();
        cardForm.setBackground(AppColors.getPanel());
        cardForm.setRadius(24);
        cardForm.setLayout(new GridBagLayout());
        cardForm.setBorder(new EmptyBorder(18, 18, 18, 18));
        cardForm.setPreferredSize(new Dimension(720, 420));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 18, 10, 18);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // ===== ID TARJETA (solo lectura) =====
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("ID Tarjeta:"), gbc);

        lblIdTarjeta = new JLabel("---");
        lblIdTarjeta.setFont(new Font("Consolas", Font.BOLD, 14));
        lblIdTarjeta.setForeground(AppColors.getPrimary());
        lblIdTarjeta.setOpaque(true);
        lblIdTarjeta.setBackground(AppColors.getPanelHover());
        lblIdTarjeta.setBorder(new EmptyBorder(6, 12, 6, 12));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(lblIdTarjeta, gbc);

        // ===== NOMBRE =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("Nombre:"), gbc);

        txtNombre = createStyledTextField(FIELD_WIDTH);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(txtNombre, gbc);

        // ===== APELLIDO =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("Apellido:"), gbc);

        txtApellido = createStyledTextField(FIELD_WIDTH);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(txtApellido, gbc);

        // ===== CÉDULA =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("Cédula:"), gbc);

        txtCedula = createStyledTextField(FIELD_WIDTH);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(txtCedula, gbc);

        // ===== EDAD Y SEXO (en la misma fila) =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("Edad:"), gbc);

        // Panel para Edad y Sexo
        JPanel rowEdadSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        rowEdadSexo.setOpaque(false);

        txtEdad = createStyledTextField(90);
        rowEdadSexo.add(txtEdad);

        JLabel lblSexo = makeLabel("Sexo:");
        rowEdadSexo.add(lblSexo);

        // ComboBox de Sexo con mejor estilo
        cmbSexo = new JComboBox<>(new String[]{"", "Masculino", "Femenino"});
        cmbSexo.setFont(AppFonts.normal());
        cmbSexo.setPreferredSize(new Dimension(160, FIELD_HEIGHT));
        cmbSexo.setBackground(Color.WHITE);
        cmbSexo.setForeground(AppColors.getTextPrimary());
        cmbSexo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppColors.getBorder(), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        // Renderizador personalizado para mejor apariencia
        cmbSexo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(AppFonts.normal());
                setBorder(new EmptyBorder(8, 10, 8, 10));
                if (isSelected) {
                    setBackground(AppColors.getPrimary());
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(AppColors.getTextPrimary());
                }
                if (value == null || value.toString().isEmpty()) {
                    setText("Seleccionar...");
                    setForeground(AppColors.getTextSecondary());
                }
                return this;
            }
        });
        rowEdadSexo.add(cmbSexo);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(rowEdadSexo, gbc);

        // ===== AULA =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        cardForm.add(makeLabel("Aula (Curso):"), gbc);

        txtAula = createStyledTextField(FIELD_WIDTH);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        cardForm.add(txtAula, gbc);

        // ===== BOTONES =====
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(22, 25, 16, 25);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        btnCancelar = CustomButton.danger("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 46));
        btnCancelar.setFont(AppFonts.boldNormal());

        btnGuardar = CustomButton.success("Guardar");
        btnGuardar.setPreferredSize(new Dimension(150, 46));
        btnGuardar.setFont(AppFonts.boldNormal());

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);
        cardForm.add(buttonPanel, gbc);

        cardForm.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(cardForm);

        add(mainContainer);
    }

    // ===== Métodos de utilidad =====

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(AppFonts.boldNormal());
        lbl.setForeground(AppColors.getTextPrimary());
        return lbl;
    }

    private CustomTextField createStyledTextField(int width) {
        CustomTextField field = new CustomTextField(20);
        field.setFont(AppFonts.normal());
        field.setPreferredSize(new Dimension(width, FIELD_HEIGHT));
        field.setBackground(Color.WHITE);
        field.setForeground(Color.DARK_GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppColors.getBorder(), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    // ===== Métodos públicos =====

    public void setCardId(String id) {
        lblIdTarjeta.setText(id != null ? id : "---");
    }

    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    /**
     * Obtiene los datos del formulario como un EstudianteDTO.
     * Realiza validaciones básicas de tipo.
     */
    public EstudianteDTO obtenerDatos() {
        EstudianteDTO dto = new EstudianteDTO();

        dto.setIdTarjeta(lblIdTarjeta.getText().trim());
        dto.setNombre(txtNombre.getText().trim());
        dto.setApellido(txtApellido.getText().trim());
        dto.setCedula(txtCedula.getText().trim());

        // Edad con validación
        try {
            String edadStr = txtEdad.getText().trim();
            dto.setEdad(edadStr.isEmpty() ? 0 : Integer.parseInt(edadStr));
        } catch (NumberFormatException e) {
            dto.setEdad(0);
        }

        // Sexo: índice 1 = Masculino (valor 1), índice 2 = Femenino (valor 2)
        int selectedIndex = cmbSexo.getSelectedIndex();
        dto.setSexo(selectedIndex > 0 ? selectedIndex : 1);

        // Aula/IdCurso con validación
        try {
            String aulaStr = txtAula.getText().trim();
            dto.setAula(aulaStr.isEmpty() ? 1 : Integer.parseInt(aulaStr));
        } catch (NumberFormatException e) {
            dto.setAula(1); // Valor por defecto
        }

        // Estado activo por defecto
        dto.setEstado("A");

        return dto;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtEdad.setText("");
        txtAula.setText("");
        cmbSexo.setSelectedIndex(0);
        lblIdTarjeta.setText("---");
    }

    /**
     * Valida que los campos obligatorios estén completos.
     * 
     * @return null si todo es válido, o mensaje de error si hay problemas
     */
    public String validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            return "El nombre es obligatorio.";
        }
        if (txtApellido.getText().trim().isEmpty()) {
            return "El apellido es obligatorio.";
        }
        if (txtCedula.getText().trim().isEmpty()) {
            return "La cédula es obligatoria.";
        }
        if (txtCedula.getText().trim().length() != 10) {
            return "La cédula debe tener 10 dígitos.";
        }
        if (txtEdad.getText().trim().isEmpty()) {
            return "La edad es obligatoria.";
        }
        try {
            int edad = Integer.parseInt(txtEdad.getText().trim());
            if (edad < 1 || edad > 100) {
                return "La edad debe estar entre 1 y 100.";
            }
        } catch (NumberFormatException e) {
            return "La edad debe ser un número válido.";
        }
        // Validar que se haya seleccionado un sexo
        if (cmbSexo.getSelectedIndex() == 0) {
            return "Debe seleccionar el sexo.";
        }
        if (txtAula.getText().trim().isEmpty()) {
            return "El aula es obligatoria.";
        }
        try {
            int aula = Integer.parseInt(txtAula.getText().trim());
            if (aula < 1) {
                return "El aula debe ser un número positivo.";
            }
        } catch (NumberFormatException e) {
            return "El aula debe ser un número válido.";
        }
        if (lblIdTarjeta.getText().equals("---") || lblIdTarjeta.getText().trim().isEmpty()) {
            return "No hay una tarjeta RFID asociada.";
        }
        return null; // Todo válido
    }
}
