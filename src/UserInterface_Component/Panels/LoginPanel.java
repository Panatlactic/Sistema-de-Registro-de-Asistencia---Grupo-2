package UserInterface_Component.Panels;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomButton;
import UserInterface_Component.Components.CustomPasswordField;
import UserInterface_Component.Components.CustomSecondPanel;
import UserInterface_Component.Components.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Panel de login para tutores.
 * Diseño unificado con el resto de la aplicación.
 */
public class LoginPanel extends JPanel {

    private CustomTextField txtUser;
    private CustomPasswordField txtPassword;
    private CustomButton btnIngresar;
    private JLabel lblError;

    // Control para evitar múltiples listeners
    private boolean listenerConfigured = false;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        setBackground(AppColors.getBackground());

        // ===== CONTENEDOR PRINCIPAL =====
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setOpaque(false);

        // ===== TÍTULO SUPERIOR =====
        JLabel lblTitle = new JLabel("Sistema de Asistencia RFID");
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 28));
        lblTitle.setForeground(AppColors.getTextPrimary());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblTitle);
        mainContainer.add(Box.createVerticalStrut(15));

        // Subtítulo
        JLabel lblSubtitle = new JLabel("Control de asistencia estudiantil");
        lblSubtitle.setFont(AppFonts.normal());
        lblSubtitle.setForeground(AppColors.getTextSecondary());
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblSubtitle);
        mainContainer.add(Box.createVerticalStrut(40));

        // ===== CARD DE LOGIN =====
        CustomSecondPanel cardPanel = new CustomSecondPanel();
        cardPanel.setBackground(AppColors.getPanel());
        cardPanel.setRadius(20);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setPreferredSize(new Dimension(420, 400));
        cardPanel.setMaximumSize(new Dimension(420, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 30, 8, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        // Icono de usuario (simulado con texto)
        JLabel lblIcon = new JLabel("👤", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblIcon.setForeground(AppColors.getPrimary());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 30, 5, 30);
        cardPanel.add(lblIcon, gbc);

        // Título del card
        JLabel lblLoginTitle = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblLoginTitle.setFont(new Font("Helvetica", Font.BOLD, 22));
        lblLoginTitle.setForeground(AppColors.getTextPrimary());
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 30, 5, 30);
        cardPanel.add(lblLoginTitle, gbc);

        // Descripción
        JLabel lblDesc = new JLabel("Ingresa tus credenciales de tutor", SwingConstants.CENTER);
        lblDesc.setFont(AppFonts.small());
        lblDesc.setForeground(AppColors.getTextSecondary());
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 30, 20, 30);
        cardPanel.add(lblDesc, gbc);

        // Campo Usuario
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 30, 5, 30);
        txtUser = new CustomTextField(25);
        txtUser.setFont(AppFonts.normal());
        txtUser.setPreferredSize(new Dimension(340, 42));
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppColors.getBorder(), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Placeholder para usuario
        JPanel userPanel = createFieldPanel("Usuario", txtUser);
        cardPanel.add(userPanel, gbc);

        // Campo Contraseña
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 30, 5, 30);
        txtPassword = new CustomPasswordField(25);
        txtPassword.setFont(AppFonts.normal());
        txtPassword.setPreferredSize(new Dimension(340, 42));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppColors.getBorder(), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        JPanel passPanel = createFieldPanel("Contraseña", txtPassword);
        cardPanel.add(passPanel, gbc);

        // Label de error
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 30, 5, 30);
        lblError = new JLabel(" ", SwingConstants.CENTER);
        lblError.setFont(AppFonts.small());
        lblError.setForeground(AppColors.getError());
        cardPanel.add(lblError, gbc);

        // Botón Ingresar
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 30, 25, 30);
        btnIngresar = CustomButton.success("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(340, 48));
        btnIngresar.setFont(new Font("Helvetica", Font.BOLD, 15));
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cardPanel.add(btnIngresar, gbc);

        // Permitir Enter para hacer login
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnIngresar.doClick();
                }
            }
        };
        txtUser.addKeyListener(enterKeyAdapter);
        txtPassword.addKeyListener(enterKeyAdapter);

        // Añadir card al contenedor
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(cardPanel);
        mainContainer.add(Box.createVerticalStrut(30));

        // ===== FOOTER =====
        JLabel lblFooter = new JLabel("© 2026 - Sistema de Asistencia RFID v1.0");
        lblFooter.setFont(AppFonts.small());
        lblFooter.setForeground(AppColors.getTextSecondary());
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblFooter);

        add(mainContainer);
    }

    /**
     * Crea un panel con etiqueta y campo de texto.
     */
    private JPanel createFieldPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(AppFonts.boldNormal());
        label.setForeground(AppColors.getTextPrimary());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(field);

        return panel;
    }


    // ===== Métodos públicos =====

    public String getUsuario() {
        return txtUser.getText().trim();
    }

    public String getClave() {
        return new String(txtPassword.getPassword());
    }

    public void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setForeground(AppColors.getError());
    }

    public void limpiarError() {
        lblError.setText(" ");
    }

    public void limpiarCampos() {
        txtUser.setText("");
        txtPassword.setText("");
        limpiarError();
    }

    public void addIngresarListener(ActionListener listener) {
        if (!listenerConfigured) {
            btnIngresar.addActionListener(listener);
            listenerConfigured = true;
        }
    }

    public void enfocarUsuario() {
        SwingUtilities.invokeLater(() -> txtUser.requestFocusInWindow());
    }
}
