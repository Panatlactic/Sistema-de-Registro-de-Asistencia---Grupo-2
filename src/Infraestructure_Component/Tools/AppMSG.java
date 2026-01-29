package Infraestructure_Component.Tools;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class AppMSG {

    private AppMSG() {
    } // Evita instanciar

    /** Mensaje simple */
    public static final void show(String msg) {
        show(msg, AppColors.getTextPrimary(), AppFonts.normal(), AppColors.getBackground());
    }

    /** Mensaje de error simple */
    public static final void showError(String msg) {
        show(msg, AppColors.getTextPrimary(), AppFonts.boldNormal(), AppColors.getError());
    }

    public static final void showWarning(String msg) {
        show(msg, AppColors.getTextPrimary(), AppFonts.normal(), AppColors.getWarning());
    }

    public static final void showSuccess(String msg) {
        show(msg, AppColors.getTextPrimary(), AppFonts.normal(), AppColors.getSuccess());
    }

    public static final void showErrorCritico(String msg) {
        JOptionPane.showMessageDialog(null, "Error crítico: " + msg, "Sistema de Asistencia RFID",  JOptionPane.ERROR_MESSAGE);
    }


    public static final boolean showConfirmYesNo(String msg) {
        return showConfirmYesNo(msg, AppColors.getTextPrimary(), AppFonts.normal(), AppColors.getBackground());
    }

  
    private static final void show(String msg, Color fgColor, Font font, Color bgColor) {
        JLabel label = new JLabel(msg);
        label.setForeground(fgColor);
        label.setFont(font);

        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.add(label);

        JOptionPane.showMessageDialog(null, panel, "Sistema de Asistencia RFID", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Confirmación Sí/No con colores personalizados
     * 
     * @param msg     Texto del mensaje
     * @param fgColor Color del texto
     * @param font    Fuente del texto
     * @param bgColor Color de fondo
     * @return true si el usuario presiona "Sí"
     */
    private static final boolean showConfirmYesNo(String msg, Color fgColor, Font font, Color bgColor) {
        JLabel label = new JLabel(msg);
        label.setForeground(fgColor);
        label.setFont(font);

        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.add(label);

        int result = JOptionPane.showConfirmDialog(null, panel, "Sistema de Asistencia RFID",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

}
