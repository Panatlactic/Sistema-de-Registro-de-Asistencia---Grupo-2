package UserInterface_Component.Components;

import javax.swing.*;

import Infraestructure_Component.AppColors;
import Infraestructure_Component.AppFonts;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);

        // Fuente y colores
        setFont(AppFonts.normal());
        setBackground(AppColors.getPrimary());
        setForeground(AppColors.getTextPrimary());

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
        btn.setBackground(AppColors.getPrimary());
        btn.setForeground(AppColors.getTextPrimary());
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
