package UserInterface_Component.Styles;

import java.awt.Color;
import java.io.InputStream;
import java.util.Properties;

public abstract class UIConfig {

    private static final Properties props = new Properties();

    //AQUI SOLO SE CREO EL METODO PARA LEER EL ARCHIVO app.properties

    static {
        try (InputStream is = UIConfig.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {

            if (is == null) {
                throw new RuntimeException("No se encontró app.properties");
            }
            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando app.properties", e);
        }
    }

    public static String text(String key) {
        return props.getProperty(key);
    }

    public static Color color(String key) {
        return Color.decode(props.getProperty(key));
    }

    public static String image(String key) {
        return props.getProperty(key);
    }
}