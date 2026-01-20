package UserInterface_Component.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StartPanel extends JPanel {

    private JComboBox<String> portSelector;
    private JButton btnConnect;
    private JLabel statusLabel;

    public StartPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Configuración de Conexión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Port Selector
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Puerto Arduino:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        portSelector = new JComboBox<>();
        portSelector.setPreferredSize(new Dimension(200, 30));
        add(portSelector, gbc);

        // Connect Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnConnect = new JButton("Conectar e Iniciar");
        btnConnect.setPreferredSize(new Dimension(180, 40));
        btnConnect.setFont(new Font("Arial", Font.BOLD, 14));
        btnConnect.setBackground(new Color(66, 133, 244)); 
        btnConnect.setFocusPainted(false);
        add(btnConnect, gbc);

        // Status Label
        gbc.gridy = 3;
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        add(statusLabel, gbc);
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
        statusLabel.setForeground(isError ? Color.RED : Color.green.darker());
    }
}
