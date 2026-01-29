package UserInterface_Component.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;

public class AttendanceTableConfig  {
    public static void configureTable(JTable table) {
        table.setRowHeight(36);
        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setGridColor(AppColors.getBorder());
        table.setFont(AppFonts.normal());
        table.setForeground(AppColors.getTextPrimary());
        table.setBackground(AppColors.getPanel());
        table.setOpaque(true);
        table.setSelectionBackground(AppColors.getPanelHover());
        table.setSelectionForeground(AppColors.getTextPrimary());
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBorder(null);

        // ===== HEADER =====
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(44, 62, 80));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setReorderingAllowed(false);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value != null ? value.toString() : "");
                label.setOpaque(true);
                label.setBackground(new Color(44, 62, 80));
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.BOLD, 13));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, AppColors.getPrimary()),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                return label;
            }
        });

        // ===== CELDAS =====
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color ROW_EVEN = AppColors.getPanel();
            private final Color ROW_ODD = AppColors.getPanelHover();
            private final Color HOVER_COLOR = new Color(26, 57, 92);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                cell.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
                cell.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                if (isSelected) {
                    cell.setBackground(HOVER_COLOR);
                } else {
                    cell.setBackground(row % 2 == 0 ? ROW_EVEN : ROW_ODD);
                }
                cell.setForeground(AppColors.getTextPrimary());

                if (column == 5 && value != null) {
                    String estado = value.toString().trim().toUpperCase();
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                    cell.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    if ("A".equals(estado) || "ACTIVO".equals(estado)) {
                        cell.setForeground(AppColors.getSuccess());
                    } else if ("I".equals(estado) || "INACTIVO".equals(estado)) {
                        cell.setForeground(AppColors.getError());
                    }
                } else if (column == 0) {
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                } else if (column == 3) {
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                } else if (column == 4) {
                    cell.setFont(new Font("Consolas", Font.PLAIN, 12));
                    cell.setForeground(new Color(200, 210, 220));
                } else {
                    cell.setHorizontalAlignment(SwingConstants.LEFT);
                }

                return cell;
            }
        });

        // Anchos de columna proporcionales
        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // Hora
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Cédula
        table.getColumnModel().getColumn(2).setPreferredWidth(160); // Nombre
        table.getColumnModel().getColumn(3).setPreferredWidth(60);  // Aula
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Tarjeta
        table.getColumnModel().getColumn(5).setPreferredWidth(70);  // Estado
    }


    public static void replaceTableInRightPanel(JPanel rightPanel, JTable table) {
        Component center = ((BorderLayout) rightPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        if (center != null)
            rightPanel.remove(center);

        // Contenedor con el mismo color del panel (sin efecto opaco)
        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBackground(AppColors.getPanel());
        tableContainer.setOpaque(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(AppColors.getPanel());
        scroll.getViewport().setOpaque(true);
        scroll.setBackground(AppColors.getPanel());
        scroll.setOpaque(false);
        scroll.setBorder(BorderFactory.createLineBorder(AppColors.getBorder(), 1));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Scrollbar
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setBackground(AppColors.getPanel());

        tableContainer.add(scroll, BorderLayout.CENTER);
        tableContainer.setBorder(AppUIConstants.emptyBorder(AppUIConstants.PADDING_S));

        rightPanel.add(tableContainer, BorderLayout.CENTER);

        rightPanel.revalidate();
        rightPanel.repaint();
    }

}
