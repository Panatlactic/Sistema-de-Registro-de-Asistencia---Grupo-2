package UserInterface_Component.Components;

import javax.swing.*;
import java.awt.*;

import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppUIConstants;

public class CustomPasswordField extends JPasswordField {

    public CustomPasswordField(int columns) {
        super(columns);
        setFont(AppFonts.normal());
        setBackground(Color.WHITE);
        setForeground(AppColors.getTextSecondary().darker());
        setBorder(AppUIConstants.bordered(AppColors.getBorder(), AppUIConstants.PADDING_S));
    }
}
