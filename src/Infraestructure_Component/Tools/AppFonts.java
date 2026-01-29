package Infraestructure_Component.Tools;
import java.awt.Font;

import Infraestructure_Component.AppConfig;

public abstract class AppFonts {

    private AppFonts() {
    }

    // Keys
    private static final String FONT_FAMILY = "font.family";

    // Tamaños estándar
    public static final int SIZE_SMALL = 10;
    public static final int SIZE_NORMAL = 14;
    public static final int SIZE_LARGE = 18; 

    /*
     * =========================
     * Getters de fuentes
     * =========================
     */

    private static Font font(int style, int size) {
        return new Font(AppConfig.getProperty(FONT_FAMILY), style, size);
    }

    public static Font small() {
        return font(Font.BOLD, SIZE_SMALL);
    }

    public static Font normal() {
        return font(Font.BOLD, SIZE_NORMAL);
    }

    public static Font large() {
        return font(Font.BOLD, SIZE_LARGE);
    }

    public static Font boldSmall() {
        return font(Font.BOLD, SIZE_SMALL);
    }

    public static Font boldNormal() {
        return font(Font.BOLD, SIZE_NORMAL);
    }

    public static Font boldLarge() {
        return font(Font.BOLD, SIZE_LARGE);
    }
}
