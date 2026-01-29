package UserInterface_Component.Components;

import javax.swing.*;
import java.awt.*;

import Infraestructure_Component.Tools.AppUIConstants;

public class CustomSecondPanel extends JPanel {

    private int radius = 20;

    public CustomSecondPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        return new Insets(AppUIConstants.PADDING_M, AppUIConstants.PADDING_M, AppUIConstants.PADDING_M,
                AppUIConstants.PADDING_M);
    }
}
