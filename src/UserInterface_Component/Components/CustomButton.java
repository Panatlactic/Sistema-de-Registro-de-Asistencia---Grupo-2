package UserInterface_Component.Components;

import javax.swing.*;
import UserInterface_Component.Styles.UITheme;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);

        // Fuente y colores
        setFont(UITheme.SUBTITLE);
        setBackground(UITheme.PRIMARY);
        setForeground(UITheme.TEXT_PRIMARY);

        // IMPORTANTE: esto hace que el botón realmente muestre el color PRIMARY
        setOpaque(true);
        setContentAreaFilled(true);  
        setBorderPainted(false);

        // Otros ajustes visuales
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Botón de éxito (PRIMARY)
    public static CustomButton createSuccessButton(String text) {
        CustomButton btn = new CustomButton(text);
        btn.setBackground(UITheme.PRIMARY);
        btn.setForeground(UITheme.TEXT_PRIMARY);
        return btn;
    }

    // Botón de peligro (rojo)
    public static CustomButton createDangerButton(String text) {
        CustomButton btn = new CustomButton(text);
        btn.setBackground(new Color(220, 53, 69));
        btn.setForeground(Color.WHITE);
        return btn;
    }
}
