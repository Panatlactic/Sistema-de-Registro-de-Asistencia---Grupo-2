import Controllers.MainController;
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