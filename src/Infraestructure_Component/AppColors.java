package Infraestructure_Component;
import java.awt.Color;

import Infraestructure_Component.Tools.CMD;

public abstract class AppColors {

    private AppColors() {
    }

    // Llaves de colores
    private static final String COLOR_PRIMARY = "color.primary";
    private static final String COLOR_PRIMARY_HOVER = "color.primary.hover";
    private static final String COLOR_SECONDARY = "color.secondary";
    private static final String COLOR_SECONDARY_HOVER = "color.secondary.hover";
    private static final String COLOR_ACCENT = "color.accent";
    private static final String COLOR_ACCENT_HOVER = "color.accent.hover";
    private static final String COLOR_BACKGROUND = "color.background";
    private static final String COLOR_PANEL = "color.panel";
    private static final String COLOR_PANEL_HOVER = "color.panel.hover";
    private static final String COLOR_TEXT_PRIMARY = "color.text.primary";
    private static final String COLOR_TEXT_SECONDARY = "color.text.secondary";
    private static final String COLOR_BORDER = "color.border";
    private static final String COLOR_SUCCESS = "color.success";
    private static final String COLOR_ERROR = "color.error";
    private static final String COLOR_WARNING = "color.warning";

    // Método general para convertir "#RRGGBB" a Color
    private static Color hexToColor(String hex) {
        if (hex == null)
            return Color.WHITE;
        if (hex.startsWith("#"))
            hex = hex.substring(1);
        try {
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);
            return new Color(r, g, b);
        } catch (Exception e) {
            CMD.printlnError("ERROR al convertir color ❱❱ " + hex);
            return Color.WHITE;
        }
    }

    // Métodos públicos para cada color
    public static Color getPrimary() {
        return hexToColor(AppConfig.getProperty(COLOR_PRIMARY));
    }

    public static Color getPrimaryHover() {
        return hexToColor(AppConfig.getProperty(COLOR_PRIMARY_HOVER));
    }

    public static Color getSecondary() {
        return hexToColor(AppConfig.getProperty(COLOR_SECONDARY));
    }

    public static Color getSecondaryHover() {
        return hexToColor(AppConfig.getProperty(COLOR_SECONDARY_HOVER));
    }

    public static Color getAccent() {
        return hexToColor(AppConfig.getProperty(COLOR_ACCENT));
    }

    public static Color getAccentHover() {
        return hexToColor(AppConfig.getProperty(COLOR_ACCENT_HOVER));
    }

    public static Color getBackground() {
        return hexToColor(AppConfig.getProperty(COLOR_BACKGROUND));
    }

    public static Color getPanel() {
        return hexToColor(AppConfig.getProperty(COLOR_PANEL));
    }

    public static Color getPanelHover() {
        return hexToColor(AppConfig.getProperty(COLOR_PANEL_HOVER));
    }

    public static Color getTextPrimary() {
        return hexToColor(AppConfig.getProperty(COLOR_TEXT_PRIMARY));
    }

    public static Color getTextSecondary() {
        return hexToColor(AppConfig.getProperty(COLOR_TEXT_SECONDARY));
    }

    public static Color getBorder() {
        return hexToColor(AppConfig.getProperty(COLOR_BORDER));
    }

    public static Color getSuccess() {
        return hexToColor(AppConfig.getProperty(COLOR_SUCCESS));
    }

    public static Color getError() {
        return hexToColor(AppConfig.getProperty(COLOR_ERROR));
    }

    public static Color getWarning() {
        return hexToColor(AppConfig.getProperty(COLOR_WARNING));
    }

}
