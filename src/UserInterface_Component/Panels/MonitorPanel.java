package UserInterface_Component.Panels;

import DataAccess_Component.DTOs.EstudianteDTO;
import javax.swing.*;
import java.awt.*;

public class MonitorPanel extends JPanel {

    private JLabel lblNombre;
    private JLabel lblCedula;
    private JLabel lblEstado;
    private JLabel lblFoto;
    private JLabel lblMensaje;

    public MonitorPanel() {
        setLayout(new BorderLayout());

        // --- Header ---
        JLabel title = new JLabel("Monitor de Asistencia", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // --- Center Info ---
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Foto
        lblFoto = new JLabel();
        lblFoto.setPreferredSize(new Dimension(150, 150));
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setText("FOTO");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        infoPanel.add(lblFoto, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("Nombre:"), gbc);

        lblNombre = new JLabel("---");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 2;
        infoPanel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Cédula:"), gbc);

        lblCedula = new JLabel("---");
        lblCedula.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 2;
        infoPanel.add(lblCedula, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Estado:"), gbc);

        lblEstado = new JLabel("---");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 2;
        infoPanel.add(lblEstado, gbc);

        add(infoPanel, BorderLayout.CENTER);

        // --- Footer Message ---
        lblMensaje = new JLabel("Esperando tarjeta...", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Consolas", Font.ITALIC, 14));
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblMensaje, BorderLayout.SOUTH);
    }

    public void mostrarEstudiante(EstudianteDTO estudiante) {
        lblNombre.setText(estudiante.getNombre() + " " + estudiante.getApellido());
        lblCedula.setText(estudiante.getCedula());
        lblEstado.setText(estudiante.getEstado());
        lblEstado.setForeground("A".equals(estudiante.getEstado()) ? Color.GREEN.darker() : Color.RED);

        // Show Photo
        if (estudiante.getFotoPath() != null && !estudiante.getFotoPath().isEmpty()) {
            ImageIcon icon = new ImageIcon(estudiante.getFotoPath());
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
            lblFoto.setText("");
        } else {
            lblFoto.setIcon(null);
            lblFoto.setText("SIN FOTO");
        }

        lblMensaje.setText("¡Acceso Correcto!");
        lblMensaje.setForeground(Color.BLUE);
    }

    public void reset() {
        lblNombre.setText("---");
        lblCedula.setText("---");
        lblEstado.setText("---");
        lblFoto.setIcon(null);
        lblFoto.setText("FOTO");
        lblMensaje.setText("Esperando tarjeta...");
        lblMensaje.setForeground(Color.BLACK);
    }

    public void mostrarMensaje(String msg) {
        lblMensaje.setText(msg);
    }
}
