package UserInterface_Component.Styles;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public abstract class UITheme {

    private UITheme() {}

    //AQUI ESTAN LOS COLORS Y FONTS USADOS EN LA APP, PUEDES CREAR MAS SI LO CONSIDERAS NECESARIO

    // colors

    public static final Color PRIMARY =
            UIConfig.color("color.primary");

    public static final Color PRIMARY_HOVER =
            UIConfig.color("color.primary.hover");

    public static final Color BACKGROUND =
            UIConfig.color("color.background");

    public static final Color PANEL =
            UIConfig.color("color.panel");

    public static final Color PANEL_HOVER =
            UIConfig.color("color.panel.hover");

    public static final Color TEXT_PRIMARY =
            UIConfig.color("color.text.primary");

    public static final Color TEXT_SECONDARY =
            UIConfig.color("color.text.secondary");

    // FONTS

    private static final String FONT_FAMILY =
            UIConfig.text("font.family");

    public static final Font TITLE =
            new Font(FONT_FAMILY, Font.BOLD, 60);

    public static final Font SUBTITLE =
            new Font(FONT_FAMILY, Font.PLAIN, 18);

    public static final Font TEXT =
            new Font(FONT_FAMILY, Font.PLAIN, 14);

    public static final Font ORNAMENT_LOGIN =
            new Font(FONT_FAMILY, Font.BOLD, 30);

    // ORNAMENT TEXT

    public static final JLabel LOGIN_LABEL = createLoginLabel();

    private static JLabel createLoginLabel() {
        JLabel lbl = new JLabel(UIConfig.text("app.login")); // "LOGIN"
        lbl.setFont(ORNAMENT_LOGIN);             // Fuente: negrita, tamaño 22
        lbl.setForeground(UITheme.TEXT_PRIMARY); // Color blanco
        return lbl;
    }

    public static final JLabel LOGO_LABEL = createLogoLabel();

    private static JLabel createLogoLabel() {
        JLabel lbl = new JLabel(UIConfig.text("app.name")); // "CHECKLYN"
        lbl.setFont(TITLE);        // fuente grande para logo
        lbl.setForeground(TEXT_PRIMARY); // color blanco
        return lbl;
    }




}