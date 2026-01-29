package UserInterface_Component.Form;

import javax.swing.*;
import java.awt.BorderLayout;

import Infraestructure_Component.Tools.AppResources;

public abstract class SplashScreenForm {

    private static JFrame frmSplash;
    private static JProgressBar prbLoading;
    private static JLabel lblSplash;

    public static void Show() {

        SwingUtilities.invokeLater(() -> {
            ImageIcon icoImage = new ImageIcon(AppResources.getImgSplash());
            lblSplash = new JLabel(icoImage);
            prbLoading = new JProgressBar(0, 100);
            prbLoading.setStringPainted(true);

            frmSplash = new JFrame();
            frmSplash.setUndecorated(true);
            frmSplash.add(lblSplash, BorderLayout.CENTER);
            frmSplash.add(prbLoading, BorderLayout.SOUTH);
            frmSplash.setSize(icoImage.getIconWidth(), icoImage.getIconHeight());
            frmSplash.setLocationRelativeTo(null);
            frmSplash.setVisible(true);
        });

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }

                final int value = i;
                SwingUtilities.invokeLater(() -> prbLoading.setValue(value));
            }

            SwingUtilities.invokeLater(() -> frmSplash.dispose());
        }).start();
    }
}
