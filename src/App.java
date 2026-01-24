import Controllers.MainController;
import UserInterface_Component.MainFrame;
import UserInterface_Component.Panels.MonitorPanel;
import UserInterface_Component.Panels.RegistroPanel;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(MainController::new);
    }
}