package UserInterface_Component.Styles;

import javax.swing.JLabel;

import Infraestructure_Component.AppColors;
import Infraestructure_Component.AppConfig;
import Infraestructure_Component.AppFonts;

public abstract class AppLabels {

    private AppLabels() {}

    public static final JLabel LOGIN_LABEL = createLoginLabel();

    private static JLabel createLoginLabel() {
        JLabel lbl = new JLabel(AppConfig.getProperty("app.login")); // "LOGIN"
        lbl.setFont(AppFonts.boldLarge());             // Fuente: negrita, tamaño 22
        lbl.setForeground(AppColors.getTextPrimary()); // Color blanco
        return lbl;
    }

    public static final JLabel LOGO_LABEL = createLogoLabel();

    private static JLabel createLogoLabel() {
        JLabel lbl = new JLabel(AppConfig.getProperty("app.name")); // "CHECKLYN"
        lbl.setFont(AppFonts.boldLarge());        // fuente grande para logo
        lbl.setForeground(AppColors.getTextPrimary()); // color blanco
        return lbl;
    }




}