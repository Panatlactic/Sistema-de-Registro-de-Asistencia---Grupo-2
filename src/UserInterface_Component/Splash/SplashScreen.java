package UserInterface_Component.Splash;

import javax.swing.*;

import Infraestructure_Component.AppColors;
import Infraestructure_Component.AppResources;

import java.awt.*;

public class SplashScreen {

    private JWindow splashWindow;
    private JProgressBar progressBar;

    public SplashScreen() {
        // Crear ventana sin bordes
        splashWindow = new JWindow();
        splashWindow.setSize(800, 500);
        splashWindow.setLocationRelativeTo(null);
        splashWindow.setLayout(null);

        // Imagen de fondo (SplashArt)
        JLabel background = new JLabel(new ImageIcon(AppResources.getImgIcon())); // <- Tu imagen
        background.setBounds(0, 0, 800, 500);
        splashWindow.add(background);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setBounds(0, 470, 800, 30); // barra en la parte inferior
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setForeground(AppColors.getPrimary()); // color PRIMARY
        progressBar.setBackground(Color.LIGHT_GRAY);
        progressBar.setStringPainted(true);

        splashWindow.add(progressBar);
        splashWindow.setVisible(true);

        cargarSplash();
    }

    private void cargarSplash() {
        // Hilo para simular carga
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(30); // velocidad de la barra
                    final int valor = i;
                    SwingUtilities.invokeLater(() -> progressBar.setValue(valor));
                }
                // Cuando llegue al 100%, cerrar splash y abrir MainController
                SwingUtilities.invokeLater(() -> {
                    splashWindow.setVisible(false);
                    splashWindow.dispose();
                    new Controllers.MainController();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new SplashScreen();
    }
}
