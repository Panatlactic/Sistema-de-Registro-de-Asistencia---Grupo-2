package UserInterface_Component;

import javax.swing.*;
import java.awt.*;

import Infraestructure_Component.Tools.AppColors;

public class MainFrame extends JFrame {

    private JPanel currentPanel;

    public MainFrame() {
        initComponents("Sistema de Asistencia RFID");
        setVisible(true);
    }

    public void changePanel(JPanel newPanel) {
        this.currentPanel = newPanel;
        getContentPane().removeAll();
        getContentPane().add(newPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    private void initComponents(String titleApp) {
        setTitle(titleApp);
        setSize(1400, 700);
        setMinimumSize(new Dimension(1100, 650));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(AppColors.getBackground());
    }
}
