package UserInterface_Component.Panels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomSecondPanel;

public class RightPanel extends JPanel {
    private HomePanel homePanel;

    public RightPanel(HomePanel homePanel) {
        this.homePanel = homePanel;
        
        // Configuramos el panel actual (this)
        this.setOpaque(false);
        this.setLayout(new BorderLayout()); 

        // 2. LLAMAMOS al método que construye la interfaz y lo añadimos a "this"
        this.add(buildRightPanel(), BorderLayout.CENTER);
    }


    private JPanel buildRightPanel() {
        CustomSecondPanel right = new CustomSecondPanel();
        right.setBackground(AppColors.getPanel());
        right.setRadius(24);
        right.setLayout(new BorderLayout());
        right.setOpaque(false);

        JLabel title = makeSectionTitle("Asistencia en tiempo real");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.getBorder()),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        topBar.add(title, BorderLayout.WEST);
        
        // Hint de ayuda mejorado
        JLabel hint = new JLabel("Actualiza automáticamente al detectar tarjeta RFID");
        hint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        hint.setForeground(AppColors.getTextSecondary());
        topBar.add(hint, BorderLayout.EAST);

        JScrollPane scroll = new JScrollPane(new JTable());
        scroll.setBorder(null);

        right.add(topBar, BorderLayout.NORTH);
        right.add(scroll, BorderLayout.CENTER);

        return right;
    }

    private JLabel makeSectionTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(AppFonts.boldNormal());
        l.setForeground(AppColors.getTextPrimary());
        return l;
    }
    
}
