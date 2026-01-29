package UserInterface_Component.Panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;
import UserInterface_Component.Components.CustomSecondPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class LeftPanel extends JPanel {

    private HomePanel homePanel;

    public LeftPanel(HomePanel homePanel) {
        this.homePanel = homePanel;
        
        // Configuramos el panel actual (this)
        this.setOpaque(false);
        this.setLayout(new BorderLayout()); 

        // 2. LLAMAMOS al método que construye la interfaz y lo añadimos a "this"
        this.add(buildLeftPanel(), BorderLayout.CENTER);
    }

    private JPanel buildLeftPanel() {
        // Panel izquierdo
        JPanel leftRoot = new JPanel();
        leftRoot.setOpaque(false);
        leftRoot.setLayout(new BoxLayout(leftRoot, BoxLayout.Y_AXIS));
        leftRoot.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));

        // Panel exportar CSV
        CustomSecondPanel cardExportar = new CustomSecondPanel();
        cardExportar.setBackground(AppColors.getPanel());
        cardExportar.setRadius(24);
        cardExportar.setLayout(new BorderLayout());
        cardExportar.setOpaque(false);

        JLabel exportTitle = makeSectionTitle("Exportar Asistencias");
        exportTitle.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));


        // Delete card
        CustomSecondPanel cardDelete = new CustomSecondPanel();
        cardDelete.setBackground(AppColors.getPanel());
        cardDelete.setRadius(24);
        cardDelete.setLayout(new BorderLayout());
        cardDelete.setOpaque(false);

        JLabel deleteTitle = makeSectionTitle("Eliminar por cédula");
        deleteTitle.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));

        JPanel deleteBody = new JPanel();
        deleteBody.setOpaque(false);
        deleteBody.setLayout(new BoxLayout(deleteBody, BoxLayout.Y_AXIS));
        deleteBody.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));

        JLabel lblCed = makeLabel("Cédula");
        lblCed.setForeground(AppColors.getTextPrimary());

        JPanel rowCedula = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rowCedula.setOpaque(false);
        rowCedula.add(homePanel.getTxtCedula());
        rowCedula.add(homePanel.getBtnBuscarCedula());

        deleteBody.add(lblCed);
        deleteBody.add(Box.createVerticalStrut(6));
        deleteBody.add(rowCedula);
        deleteBody.add(Box.createVerticalStrut(12));

        deleteBody.add(makeInfoRow("Nombre:", homePanel.getLblInfoNombre()));
        deleteBody.add(makeInfoRow("Cédula:", homePanel.getLblInfoCedula()));
        deleteBody.add(makeInfoRow("Tarjeta:", homePanel.getLblInfoTarjeta()));
        deleteBody.add(makeInfoRow("Aula:", homePanel.getLblInfoAula()));
        deleteBody.add(makeInfoRow("Estado:", homePanel.getLblInfoEstado()));

        deleteBody.add(Box.createVerticalStrut(12));
        deleteBody.add(homePanel.getBtnEliminarConfirm());

        cardDelete.add(deleteTitle, BorderLayout.NORTH);
        cardDelete.add(deleteBody, BorderLayout.CENTER);

        cardExportar.add(homePanel.getBtnExportarCSV());

        leftRoot.add(cardExportar);
        leftRoot.add(Box.createVerticalStrut(14));
        leftRoot.add(cardDelete);
        leftRoot.add(Box.createVerticalGlue());

        return leftRoot;
    }

    private JLabel makeSectionTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(AppFonts.boldNormal());
        l.setForeground(AppColors.getTextPrimary());
        return l;
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(AppFonts.normal());
        l.setForeground(AppColors.getTextSecondary());
        return l;
    }

    private JPanel makeInfoRow(String key, JLabel valueLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        JLabel k = new JLabel(key);
        k.setFont(AppFonts.small());
        k.setForeground(AppColors.getTextSecondary());

        valueLabel.setFont(AppFonts.normal());
        valueLabel.setForeground(AppColors.getTextPrimary());

        row.add(k, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.CENTER);
        row.setBorder(AppUIConstants.emptyBorder(4));
        return row;
    }
}
