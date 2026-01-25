package UserInterface_Component.Panels;

import UserInterface_Component.Components.*;
import UserInterface_Component.Styles.UIStyle;
import UserInterface_Component.Styles.UITheme;
import Business_Component.Entities.LoginTutorBL;
import DataAccess_Component.DTOs.TutorDTO;
import Infraestructure_Component.AppException;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private CustomTextField txtUser;
    private CustomPasswordField txtPassword;
    private JButton btnIngresar;

    public LoginPanel() {
        // Configuración del panel principal
        setLayout(null);
        setBackground(UITheme.BACKGROUND);
        setBounds(0, 0, 1400, 900); // tamaño fijo

        // Imagen de fondo
        CustomImage iconImage = new CustomImage(UIStyle.ICON, 500, 500);
        iconImage.setBounds(100, 115, 500, 500);
        add(iconImage);

        // Logo encima de la imagen
        JLabel logoLabel = UITheme.LOGO_LABEL;
        logoLabel.setBounds(190, 520, 500, 100);
        add(logoLabel);

        // Panel principal de login
        CustomSecondPanel loginPanel = createMainPanel();
        loginPanel.setBounds(800, 200, 450, 500);
        add(loginPanel);

        // Inicializar acciones
        initActions();
    }

    private CustomSecondPanel createMainPanel() {
        CustomSecondPanel mainPanel = new CustomSecondPanel();
        mainPanel.setBackground(UITheme.PANEL); // color del panel
        mainPanel.setRadius(30);
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false); // Opaque para que el botón no se vea blanco

        // Label "Login"
        JLabel loginLabel = UITheme.LOGIN_LABEL;
        loginLabel.setBounds(75, 60, 350, 40);
        mainPanel.add(loginLabel);

        // Etiqueta Usuario
        JLabel lblUser = new JLabel("Usuario");
        lblUser.setForeground(UITheme.TEXT_PRIMARY);
        lblUser.setFont(UITheme.SUBTITLE);
        lblUser.setBounds(75, 150, 100, 25);
        mainPanel.add(lblUser);

        // Campo Usuario
        txtUser = new CustomTextField(20);
        txtUser.setBounds(75, 180, 300, 30);
        txtUser.setBackground(Color.WHITE);
        mainPanel.add(txtUser);

        // Etiqueta Contraseña
        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setForeground(UITheme.TEXT_PRIMARY);
        lblPass.setFont(UITheme.SUBTITLE);
        lblPass.setBounds(75, 250, 200, 25);
        mainPanel.add(lblPass);

        // Campo Contraseña
        txtPassword = new CustomPasswordField(20);
        txtPassword.setBounds(75, 280, 300, 30);
        txtPassword.setBackground(Color.WHITE);
        mainPanel.add(txtPassword);

        // Botón Ingresar
        btnIngresar = CustomButton.createSuccessButton("Ingresar");
        btnIngresar.setBounds(75, 370, 300, 70);
        mainPanel.add(btnIngresar);

        return mainPanel;
    }

    private void initActions() {
        btnIngresar.addActionListener(e -> {
            String usuario = txtUser.getText();
            String clave = new String(txtPassword.getPassword());

            LoginTutorBL loginBL = new LoginTutorBL();

            try {
                TutorDTO tutor = loginBL.validarLogin(usuario, clave);

                if (tutor != null) {
                    JOptionPane.showMessageDialog(this,
                            "Bienvenido " + tutor.getNombreTutor());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Usuario o contraseña incorrectos",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (AppException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error del sistema",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
