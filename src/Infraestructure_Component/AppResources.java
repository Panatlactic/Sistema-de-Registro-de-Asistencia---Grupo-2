package Infraestructure_Component;

import java.io.File;
import java.net.URL;

import Infraestructure_Component.Tools.CMD;

public abstract class AppResources {

    private static final String KEY_RES_IMG_MAIN   = "res.img.Main";
    private static final String KEY_RES_IMG_ICON   = "res.img.Icon";
    private static final String KEY_RES_IMG_SPLASH = "res.img.Splash";

    private AppResources() {}

    public static URL getImgMain() {
        return getFileURL(KEY_RES_IMG_MAIN);
    }

    public static URL getImgIcon() {
        return getFileURL(KEY_RES_IMG_ICON);
    }

    public static URL getImgSplash() {
        return getFileURL(KEY_RES_IMG_SPLASH);
    }

    private static URL getFileURL(String key) {
        try {
            String path = AppConfig.getProperty(key);

            if (path == null) {
                CMD.printlnError("Ruta null: " + key);
                return null;
            }

            // Quita el "/" inicial si existe
            if (path.startsWith("/")) {
                path = path.substring(1);
            }

            File file = new File("src/" + path);

            if (!file.exists()) {
                CMD.printlnError("NO EXISTE: " + file.getAbsolutePath());
                return null;
            }

            return file.toURI().toURL();

        } catch (Exception e) {
            CMD.printlnError("ERROR cargando recurso: " + e.getMessage());
            return null;
        }
    }
}