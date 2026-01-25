package Infraestructure_Component;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Infraestructure_Component.Tools.CMD;

public abstract class AppFonts {

    private AppFonts() {
    }

    private static final Properties props = new Properties();
    private static final String APP_PROPERTIES = "src" + File.separator + "App.properties";

    // Keys
    private static final String FONT_FAMILY = "font.family";

    // Tamaños estándar
    public static final int SIZE_SMALL = 10;
    public static final int SIZE_NORMAL = 14;
    public static final int SIZE_LARGE = 18;

    // Fallback
    private static final String FALLBACK_FAMILY = Font.MONOSPACED;

    static {
        try (InputStream appProperties = new FileInputStream(APP_PROPERTIES)) {
            props.load(appProperties);
        } catch (IOException e) {
            CMD.printlnError("ERROR al cargar ❱❱ " + e.getMessage());
        }
    }

    private static String getProperty(String key) {
        String value = props.getProperty(key);
        CMD.println("AppFonts ❱❱ " + APP_PROPERTIES + "." + key + " : " + value);
        if (value != null)
            return value;
        CMD.printlnError("ERROR ❱❱ " + APP_PROPERTIES + "." + key);
        return FALLBACK_FAMILY;
    }

    /*
     * =========================
     * Getters de fuentes
     * =========================
     */

    private static Font font(int style, int size) {
        return new Font(getProperty(FONT_FAMILY), style, size);
    }

    public static Font small() {
        return font(Font.PLAIN, SIZE_SMALL);
    }

    public static Font normal() {
        return font(Font.PLAIN, SIZE_NORMAL);
    }

    public static Font large() {
        return font(Font.PLAIN, SIZE_LARGE);
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
