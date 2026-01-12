package Infraestructure_Component.Tools;

import javax.swing.JOptionPane;

public abstract class MSG {

    private MSG() {}
    public static final void showMsg(String msg){
        JOptionPane.showMessageDialog(null, msg, "🐜 EcuAnt", JOptionPane.INFORMATION_MESSAGE);
    }
    public static final void showMsgError(String msg){
        JOptionPane.showMessageDialog(null, msg, msg, 0);
        JOptionPane.showMessageDialog(null, msg, "💀 EcuAnt", JOptionPane.OK_OPTION);
    }
    public static final boolean showConfirmYesNo(String msg){
        return (JOptionPane.showConfirmDialog(null, msg, "🐜 EcuAnt", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

}
