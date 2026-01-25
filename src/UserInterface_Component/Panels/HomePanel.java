package UserInterface_Component.Panels;

import UserInterface_Component.Components.CustomButton;
import javax.swing.*;

import Infraestructure_Component.AppColors;

public class HomePanel extends JPanel {

    private CustomButton btnRevisar, btnEliminar;

    public HomePanel() {
        // Configuración del panel principal
        setLayout(null);
        setBackground(AppColors.getBackground());
        setBounds(0, 0, 1400, 900); // mismo tamaño que el MainFrame

        // Botones superiores
        btnRevisar = CustomButton.createSuccessButton("Revisar Lista de Asistencia");
        btnEliminar = CustomButton.createSuccessButton("Eliminar Estudiante");

        // Posición y tamaño
        btnRevisar.setBounds(0, 0, 300, 70);   // esquina superior izquierda
        btnEliminar.setBounds(300, 0, 300, 70); // al lado derecho del primero

        // Agregar botones al panel
        add(btnRevisar);
        add(btnEliminar);
    }

    // Getters para poder usar los botones desde el MainController
    public CustomButton getBtnRevisar() {
        return btnRevisar;
    }

    public CustomButton getBtnEliminar() {
        return btnEliminar;
    }
}
