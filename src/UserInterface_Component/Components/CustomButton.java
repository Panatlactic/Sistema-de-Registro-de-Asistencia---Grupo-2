package UserInterface_Component.Components;

import javax.swing.*;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;

public class CustomButton extends JButton {

    public enum Variant {
        PRIMARY, SUCCESS, DANGER
    }

    public CustomButton(String text, Variant variant) {
        super(text);

        setFont(AppFonts.boldNormal());
        setForeground(AppColors.getTextPrimary());
        setCursor(AppUIConstants.CURSOR_HAND);

        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setContentAreaFilled(true);

        setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_M));
        applyBackground(Variant.PRIMARY);
    }

    public void applyBackground(Variant variant) {
        switch (variant) {
            case SUCCESS -> setBackground(AppColors.getSuccess());
            case DANGER -> setBackground(AppColors.getError());
            default -> setBackground(AppColors.getPrimary());
        }
    }

    public static CustomButton primary(String text) {
        return new CustomButton(text, Variant.PRIMARY);
    }

    public static CustomButton success(String text) {
        return new CustomButton(text, Variant.SUCCESS);
    }

    public static CustomButton danger(String text) {
        return new CustomButton(text, Variant.DANGER);
    }
}
