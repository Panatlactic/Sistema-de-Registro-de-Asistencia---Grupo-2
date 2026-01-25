package UserInterface_Component.Components;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    public CustomPanel() {
        setLayout(new BorderLayout());
    }

    public CustomPanel(LayoutManager layout) {
        super(layout);
    }

    // Método para paneles con título
    public static CustomPanel createTitledPanel(String title) {
        CustomPanel panel = new CustomPanel();
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        return panel;
    }

    // Método para paneles de formulario con GridBagLayout
    public static CustomPanel createFormPanel() {
        CustomPanel panel = new CustomPanel(new GridBagLayout());
        return panel;
    }
}