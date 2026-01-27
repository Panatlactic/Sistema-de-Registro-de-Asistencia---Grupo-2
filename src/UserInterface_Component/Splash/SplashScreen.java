package UserInterface_Component.Splash;

import javax.swing.*;
import java.awt.*;
import Infraestructure_Component.AppResources;
import Infraestructure_Component.AppColors;

public class SplashScreen {

    private JWindow window;
    private JProgressBar progressBar;

    public SplashScreen() {
        // Crear ventana sin bordes
        window = new JWindow();
        window.setSize(800, 400); // alto ajustado
        window.setLocationRelativeTo(null);

        // Panel principal que NO tiene fondo
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen de SplashArt directamente
                ImageIcon splash = new ImageIcon(AppResources.getImgSplash());
                g.drawImage(splash.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);
        window.setContentPane(panel);

        // Barra de progreso **dentro de la ventana**
        progressBar = new JProgressBar();
        progressBar.setBounds(0, 370, 800, 30); // posición ajustada dentro del panel
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setForeground(AppColors.getPrimary());
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        window.setVisible(true);

        cargarBarra();
    }

    private void cargarBarra() {
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(30); // velocidad de carga
                    final int valor = i;
                    SwingUtilities.invokeLater(() -> progressBar.setValue(valor));
                }
                SwingUtilities.invokeLater(() -> {
                    window.setVisible(false);
                    window.dispose();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
