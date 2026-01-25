package Infraestructure_Component;
import java.net.URL;
import Infraestructure_Component.Tools.CMD;

public abstract class AppResources {

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

     static URL getAppResource(String key) {
        String path = AppConfig.getProperty(key);
        if(path != null)
          return AppResources.class.getResource(path);
        else
            CMD.printlnError("ERROR ❱❱ getAppResource : " + key + " : "+ path);
        return null;
    }
}
