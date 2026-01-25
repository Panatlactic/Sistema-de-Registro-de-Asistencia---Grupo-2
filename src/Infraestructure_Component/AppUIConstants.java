package Infraestructure_Component;

import java.awt.Cursor;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class AppUIConstants {

    private AppUIConstants() {
    }

    /*
     * =========================
     * Alineaciones
     * =========================
     */
    public static final int ALIGN_LEFT = SwingConstants.LEFT;
    public static final int ALIGN_CENTER = SwingConstants.CENTER;
    public static final int ALIGN_RIGHT = SwingConstants.RIGHT;

    /*
     * =========================
     * Cursores
     * =========================
     */
    public static final Cursor CURSOR_HAND = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    public static final Cursor CURSOR_DEFAULT = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

    /*
     * ============================================================
     * Spacing (padding / margin)
     * ===========================================================
     */
    public static final int PADDING_XS = 4;
    public static final int PADDING_SM = 8;
    public static final int PADDING_MD = 12;
    public static final int PADDING_LG = 16;

    /*
     * ============================================================
     * Bordes
     * ============================================================
     * Fábrica de bordes comunes reutilizables en componentes
     */

    /**
     * Crea un borde vacío con padding uniforme.
     *
     * @param padding Espaciado interno en píxeles
     * @return Border vacío
     */
    public static Border emptyBorder(int padding) {
        return new EmptyBorder(padding, padding, padding, padding);
    }

    /**
     * Crea un borde de línea simple con el color indicado.
     *
     * @param color Color del borde
     * @return Border de tipo LineBorder
     */
    public static Border lineBorder(Color color) {
        return new LineBorder(color);
    }

    /**
     * Crea un borde compuesto:
     * - Línea externa
     * - Padding interno
     *
     * Uso típico para paneles, botones o contenedores.
     *
     * @param borderColor Color del borde externo
     * @param padding     Espaciado interno
     * @return CompoundBorder
     */
    public static CompoundBorder bordered(Color borderColor, int padding) {
        return BorderFactory.createCompoundBorder(
                new LineBorder(borderColor),
                new EmptyBorder(padding, padding, padding, padding));
    }

    /**
     * Borde estándar para paneles de la aplicación.
     * Centraliza el estilo visual de contenedores.
     *
     * @return CompoundBorder configurado para paneles
     */
    public static CompoundBorder panelBorder() {
        return bordered(AppColors.getBorder(), PADDING_SM);
    }
}