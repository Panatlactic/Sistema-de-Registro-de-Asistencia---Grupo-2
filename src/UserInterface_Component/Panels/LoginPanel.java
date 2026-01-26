package UserInterface_Component.Panels;

import UserInterface_Component.Components.*;
import UserInterface_Component.Styles.AppLabels;
import Infraestructure_Component.AppColors;
import Infraestructure_Component.AppFonts;
import Infraestructure_Component.AppResources;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private CustomTextField txtUser;
    private CustomPasswordField txtPassword;
    private JButton btnIngresar;
    private JLabel statusLabel; // Mensaje de error dentro del panel

    public LoginPanel() {
        // Configuración del panel principal
        setLayout(null);
        setBackground(AppColors.getBackground());
        setBounds(0, 0, 1400, 900); // tamaño fijo

        // Imagen de fondo
        CustomImage iconImage = new CustomImage(AppResources.getImgIcon(), 500, 500);
        iconImage.setBounds(100, 115, 500, 500);
        add(iconImage);

        // Logo encima de la imagen
        JLabel logoxLabel = AppLabels.LOGO_XLABEL;
        logoxLabel.setBounds(190, 520, 500, 100);
        add(logoxLabel);

         // Mensaje de error dentro del panel
        statusLabel = new JLabel("");
        statusLabel.setForeground(AppColors.getError());
        statusLabel.setFont(AppFonts.boldNormal());
        statusLabel.setBounds(880, 500, 300, 25); // Cambia posición y tamaño si quieres
        statusLabel.setVisible(false); // Invisible por defecto
        add(statusLabel);

        // Panel principal de login
        CustomSecondPanel loginPanel = createMainPanel();
        loginPanel.setBounds(773, 170, 450, 500);
        add(loginPanel);
    }

    private CustomSecondPanel createMainPanel() {
        CustomSecondPanel mainPanel = new CustomSecondPanel();
        mainPanel.setBackground(AppColors.getPanel()); // color del panel
        mainPanel.setRadius(30);
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false); // Opaque para que el botón no se vea blanco

        // Label "Login"
        JLabel loginLabel = AppLabels.LOGIN_LABEL;
        loginLabel.setBounds(75, 60, 350, 40);
        mainPanel.add(loginLabel);

        // Etiqueta Usuario
        JLabel lblUser = new JLabel("Usuario");
        lblUser.setForeground(AppColors.getTextPrimary());
        lblUser.setFont(AppFonts.boldNormal());
        lblUser.setBounds(75, 150, 100, 25);
        mainPanel.add(lblUser);

        // Campo Usuario
        txtUser = new CustomTextField(20);
        txtUser.setBounds(75, 180, 300, 30);
        txtUser.setBackground(Color.WHITE);
        mainPanel.add(txtUser);

        // Etiqueta Contraseña
        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setForeground(AppColors.getTextPrimary());
        lblPass.setFont(AppFonts.boldNormal());
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

    
    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public String getUser() {
        return txtUser.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    // Mostrar mensaje de error dentro del panel por un tiempo
    public void showErrorMessage(String message, int durationMs) {
        statusLabel.setText(message);
        statusLabel.setVisible(true);

        // Timer para ocultar el mensaje automáticamente
        Timer timer = new Timer(durationMs, e -> statusLabel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
}
