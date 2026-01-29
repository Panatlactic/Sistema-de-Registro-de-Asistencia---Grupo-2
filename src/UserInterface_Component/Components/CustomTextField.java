package UserInterface_Component.Components;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import Infraestructure_Component.Tools.AppColors;
import Infraestructure_Component.Tools.AppFonts;
import Infraestructure_Component.Tools.AppUIConstants;

public class CustomTextField extends JTextField {

    public CustomTextField(int columns) {
        super(columns);
        setFont(AppFonts.normal());
        setForeground(AppColors.getTextSecondary());
        setBackground(Color.WHITE);
        setBorder(AppUIConstants.bordered(AppColors.getBorder(), AppUIConstants.PADDING_S));
    }

    public static CustomTextField numeric(int columns) {
        CustomTextField field = new CustomTextField(columns);
        field.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str != null && str.matches("\\d*"))
                    super.insertString(offs, str, a);
            }
        });
        return field;
    }
}
