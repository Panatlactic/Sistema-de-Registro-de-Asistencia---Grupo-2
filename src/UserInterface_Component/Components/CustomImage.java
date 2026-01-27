package UserInterface_Component.Components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CustomImage extends JLabel {

    public CustomImage(URL imageURL, int maxWidth, int maxHeight) {
        super();

        if (imageURL == null) {
            System.err.println("Image URL is null!");
            return;
        }

        ImageIcon icon = new ImageIcon(imageURL);
        Image img = icon.getImage();

        // Escalar proporcionalmente
        double ratio = Math.min((double) maxWidth / img.getWidth(null),
                                (double) maxHeight / img.getHeight(null));
        int newW = (int) (img.getWidth(null) * ratio);
        int newH = (int) (img.getHeight(null) * ratio);

        Image scaledImg = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImg));

        // Tamaño del JLabel
        setSize(newW, newH);
        setOpaque(false);
    }

    // Posición fácil
    public void setPosition(int x, int y) {
        setLocation(x, y);
    }
}
