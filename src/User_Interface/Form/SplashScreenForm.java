package User_Interface.Form;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import User_Interface.UIStyle;

import java.awt.BorderLayout;

public abstract class SplashScreenForm {

    private static JFrame           frmSplash;
    private static JProgressBar     prbLoaging;
    private static ImageIcon        icoImage;
    private static JLabel           lblSplash;

    public static void Show() {

        icoImage = new ImageIcon(UIStyle.URL_SPLASH);
        lblSplash = new JLabel(icoImage);
        prbLoaging = new JProgressBar(0,100);

        prbLoaging.setStringPainted(true);

        frmSplash = new JFrame();
        frmSplash.setUndecorated(true);
        frmSplash.getContentPane().add(lblSplash, BorderLayout.CENTER);
        frmSplash.add(prbLoaging, BorderLayout.SOUTH);
        frmSplash.setSize(icoImage.getIconWidth(), icoImage.getIconHeight());
        frmSplash.setLocationRelativeTo(null); //Centrar en la pantalla

        frmSplash.setVisible(true);
        for (int i = 0; i <= 100; i++) {

            try {
                Thread.sleep(50); //Espera por 50 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prbLoaging.setValue(i);
        }
        frmSplash.setVisible(false);



        
    }







}
