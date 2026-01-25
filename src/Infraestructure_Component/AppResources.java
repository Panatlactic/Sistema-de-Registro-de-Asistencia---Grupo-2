package Infraestructure_Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import Infraestructure_Component.Tools.CMD;

public abstract class AppResources {

   private static final Properties props = new Properties();
   
    private static final String APP_PROPERTIES      = "src" + File.separator + "App.properties";
    private static final String KEY_RES_IMG_MAIN    = "res.img.Main";
    private static final String KEY_RES_IMG_ICON    = "res.img.Icon";
    private static final String KEY_RES_IMG_SPLASH  = "res.img.Splash";
    private AppResources() {
    }

    public static final URL getImgMain() {
        return getAppResource(KEY_RES_IMG_MAIN);
    }

    public static final URL getImgIcon() {
        return getAppResource(KEY_RES_IMG_ICON);
    }

    public static final URL getImgSplash() {
        return getAppResource(KEY_RES_IMG_SPLASH);
    }

    static {
        try (InputStream appProperties = new FileInputStream(APP_PROPERTIES)) {
            props.load(appProperties);
        } catch (IOException e) {
            CMD.printlnError("ERROR al cargar ❱❱ " + e.getMessage());
        }
    }

    static String getProperty(String key) {
        String path = props.getProperty(key);
        CMD.println("AppConfig ❱❱ "+ APP_PROPERTIES +"." + key + " : "+ path);
        if(path != null)
            return  path;
        else
            CMD.printlnError("ERROR ❱❱ " + APP_PROPERTIES +"." + key + " : "+ path);
        return null;
    }

     static URL getAppResource(String key) {
        String path = getProperty(key);
        if(path != null)
          return AppResources.class.getResource(path);
        else
            CMD.printlnError("ERROR ❱❱ getAppResource : " + APP_PROPERTIES +"." + key + " : "+ path);
        return null;
    }
}
