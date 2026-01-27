import Controllers.MainController;
import UserInterface_Component.Splash.SplashScreen;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        // Splash primero
        SplashScreen splash = new SplashScreen();

        // Esto se ejecutará cuando el splash termine (después de 100%)
        new Thread(() -> {
            try {
                // Espera a que la barra termine
                Thread.sleep(3300); // 100 pasos x 30ms = 3000ms + margen
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //levantamos la aplicación principal
            SwingUtilities.invokeLater(() -> {
                try {
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {}

                new MainController(); 
            });
        }).start();
    }
}