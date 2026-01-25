package UserInterface_Component.Styles;

import java.net.URL;

public abstract class UIStyle {

    private UIStyle() {}

        //AQUI ESTAN LAS URL DE LAS IMAGENES

    public static final URL SPLASH =
            UIStyle.class.getResource(
                    UIConfig.text("ui.splash.image")
            );

    public static final URL ICON =
            UIStyle.class.getResource(
                    UIConfig.text("ui.icon.image")
            );
}