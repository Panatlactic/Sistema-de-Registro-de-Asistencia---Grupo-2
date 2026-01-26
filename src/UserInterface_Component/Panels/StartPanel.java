package UserInterface_Component.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

import Infraestructure_Component.AppColors;
import Infraestructure_Component.AppFonts;
import UserInterface_Component.Components.CustomButton;

public class StartPanel extends JPanel {

    private JComboBox<String> portSelector;
    private CustomButton btnConnect;
    private JLabel statusLabel;
    private JLabel titleLabel;

    public StartPanel() {
        // Layout nulo para control pixel-perfect
        setLayout(null);
        setBackground(AppColors.getBackground());
        setBounds(0, 0, 1400, 900);

        // Título 
        titleLabel = new JLabel("Configuración de Conexión");
        titleLabel.setFont(AppFonts.boldxLarge());
        titleLabel.setForeground(AppColors.getTextPrimary());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 50, 1400, 70);
        add(titleLabel);

        // Label Puerto
        JLabel portLabel = new JLabel("Puerto Arduino:");
        portLabel.setForeground(AppColors.getTextPrimary());
        portLabel.setFont(AppFonts.boldLarge());
        portLabel.setBounds(500, 400, 150, 25); // x, y, ancho, alto
        add(portLabel);

        // Combo de puertos
        portSelector = new JComboBox<>();
        portSelector.setBackground(AppColors.getPanel());
        portSelector.setBounds(650, 395, 200, 30); // x, y, ancho, alto
        add(portSelector);

        // Botón Conectar (usando CustomButton)
        btnConnect = CustomButton.createSuccessButton("Conectar e Iniciar");
        btnConnect.setFont(AppFonts.boldNormal());
        btnConnect.setBounds(570, 500, 180, 40); // x, y, ancho, alto
        add(btnConnect);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(AppColors.getError());
        statusLabel.setFont(AppFonts.boldNormal());
        statusLabel.setBounds(525, 590, 400, 25); // x, y, ancho, alto
        add(statusLabel);
    }

    
    public void setPortList(Vector<String> ports) {
        portSelector.removeAllItems();
        for (String port : ports) {
            portSelector.addItem(port);
        }
    }

    public String getSelectedPort() {
        return (String) portSelector.getSelectedItem();
    }

    public void addConnectListener(ActionListener listener) {
        btnConnect.addActionListener(listener);
    }

    public void setStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? Color.RED : Color.GREEN.darker());
    }
}