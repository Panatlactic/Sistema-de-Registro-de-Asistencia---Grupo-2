package UserInterface_Component.Panels;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomButton;
import UserInterface_Component.Components.CustomSecondPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Panel de configuración de conexión con Arduino.
 * Diseño unificado con el resto de la aplicación.
 */
public class StartPanel extends JPanel {

    private JComboBox<String> portSelector;
    private CustomButton btnConnect;
    private JLabel statusLabel;

    // Control para evitar múltiples listeners
    private boolean listenerConfigured = false;

    public StartPanel() {
        setLayout(new GridBagLayout());
        setBackground(AppColors.getBackground());

        // ===== CONTENEDOR PRINCIPAL =====
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setOpaque(false);

        // ===== TÍTULO SUPERIOR =====
        JLabel lblTitle = new JLabel("Configuración de Conexión");
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 28));
        lblTitle.setForeground(AppColors.getTextPrimary());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblTitle);
        mainContainer.add(Box.createVerticalStrut(15));

        JLabel lblSubtitle = new JLabel("Conecta tu lector RFID Arduino");
        lblSubtitle.setFont(AppFonts.normal());
        lblSubtitle.setForeground(AppColors.getTextSecondary());
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(lblSubtitle);
        mainContainer.add(Box.createVerticalStrut(40));

        // ===== CARD DE CONEXIÓN =====
        CustomSecondPanel cardPanel = new CustomSecondPanel();
        cardPanel.setBackground(AppColors.getPanel());
        cardPanel.setRadius(20);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setPreferredSize(new Dimension(400, 280));
        cardPanel.setMaximumSize(new Dimension(400, 280));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 30, 12, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icono
        JLabel lblIcon = new JLabel("🔌", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblIcon.setForeground(AppColors.getPrimary());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 30, 10, 30);
        cardPanel.add(lblIcon, gbc);

        // Etiqueta Puerto
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(15, 30, 5, 10);
        JLabel lblPort = new JLabel("Puerto:");
        lblPort.setFont(AppFonts.boldNormal());
        lblPort.setForeground(AppColors.getTextPrimary());
        cardPanel.add(lblPort, gbc);

        // Selector de puerto
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 0, 5, 30);
        portSelector = new JComboBox<>();
        portSelector.setPreferredSize(new Dimension(180, 38));
        portSelector.setFont(AppFonts.normal());
        portSelector.setBackground(Color.WHITE);
        cardPanel.add(portSelector, gbc);

        // Status Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 30, 10, 30);
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(AppFonts.small());
        statusLabel.setForeground(AppColors.getTextSecondary());
        cardPanel.add(statusLabel, gbc);

        // Botón Conectar
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 30, 25, 30);
        btnConnect = CustomButton.success("Conectar e Iniciar");
        btnConnect.setPreferredSize(new Dimension(300, 50));
        btnConnect.setFont(new Font("Helvetica", Font.BOLD, 15));
        cardPanel.add(btnConnect, gbc);

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
        if (!listenerConfigured) {
            btnConnect.addActionListener(listener);
            listenerConfigured = true;
        }
    }

    public void setStatus(String message, boolean isError) {
        statusLabel.setText(message);
        if (isError) {
            statusLabel.setForeground(AppColors.getError());
        } else {
            statusLabel.setForeground(AppColors.getSuccess());
        }
    }
}
