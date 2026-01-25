package UserInterface_Component.Components;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {

    public CustomPasswordField(int columns) {
        super(columns);
        setFont(new Font("Arial", Font.PLAIN, 14));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        setBackground(Color.WHITE);
    }
}