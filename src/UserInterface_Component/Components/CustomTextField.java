package UserInterface_Component.Components;

import javax.swing.*;

import UserInterface_Component.Styles.UITheme;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomTextField extends JTextField {

    public CustomTextField(int columns) {
        super(columns);
        setFont(UITheme.TEXT);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        setBackground(Color.WHITE);

        // Placeholder effect (simple)
        addFocusListener(new FocusAdapter() {
            private String placeholder = "";
            private boolean showingPlaceholder = false;

            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                    showingPlaceholder = true;
                }
            }

            public void setPlaceholder(String placeholder) {
                this.placeholder = placeholder;
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                    showingPlaceholder = true;
                }
            }
        });
    }

    // Método para campos numéricos con validación básica
    public static CustomTextField createNumericField(int columns) {
        CustomTextField field = new CustomTextField(columns);
        field.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (str != null && str.matches("\\d*")) { // Solo números
                    super.insertString(offs, str, a);
                }
            }
        });
        return field;
    }

    // Método para campos de texto con límite de caracteres
    public static CustomTextField createLimitedTextField(int columns, int maxLength) {
        CustomTextField field = new CustomTextField(columns);
        field.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (str != null && (getLength() + str.length()) <= maxLength) {
                    super.insertString(offs, str, a);
                }
            }
        });
        return field;
    }
}