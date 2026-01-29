package UserInterface_Component.Splash;

import javax.swing.*;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppResources;

public class SplashScreen {

    private JWindow splashWindow;
    private JProgressBar progressBar;

    public SplashScreen() {
        // Crear ventana sin bordes
        splashWindow = new JWindow();
        splashWindow.setLayout(null);

        // Imagen de fondo (Splash)
        ImageIcon splashIcon = new ImageIcon(AppResources.getImgSplash());
        JLabel background = new JLabel(splashIcon);
        background.setBounds(0, 0, splashIcon.getIconWidth(), splashIcon.getIconHeight());
        splashWindow.add(background);

        splashWindow.setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
        splashWindow.setLocationRelativeTo(null);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setBounds(0, splashIcon.getIconHeight() - 30, splashIcon.getIconWidth(), 30);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setForeground(AppColors.getPrimary()); // color PRIMARY
        progressBar.setBackground(AppColors.getPanel());
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
                    new Controllers.AppStart();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
