package UserInterface_Component.Panels;

import DataAccess_Component.DTOs.EstudianteDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class RegistroPanel extends JPanel {

    //AQUI ESTA EL PANEL DE REGISTRO DE ESTUDIANTES

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtEdad;
    private JComboBox<String> cmbSexo; // 1=M, 2=F
    private JTextField txtAula;
    private JLabel lblIdTarjeta;
    private JButton btnGuardar;
    private JButton btnCancelar;

    // Photo Support
    private JButton btnFoto;
    private JLabel lblFotoPath;
    private File selectedPhotoFile;

    public RegistroPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Registrar Nuevo Estudiante", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Field Helper
        int row = 0;

        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("ID Tarjeta:"), gbc);
        lblIdTarjeta = new JLabel("---");
        lblIdTarjeta.setFont(new Font("Monospaced", Font.BOLD, 14));
        gbc.gridx = 1;
        formPanel.add(lblIdTarjeta, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(txtApellido, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Cédula:"), gbc);
        txtCedula = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(txtCedula, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Edad:"), gbc);
        txtEdad = new JTextField(5);
        gbc.gridx = 1;
        formPanel.add(txtEdad, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Sexo:"), gbc);
        cmbSexo = new JComboBox<>(new String[] { "Maculino", "Femenino" });
        gbc.gridx = 1;
        formPanel.add(cmbSexo, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Aula:"), gbc);
        txtAula = new JTextField(5);
        gbc.gridx = 1;
        formPanel.add(txtAula, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Foto:"), gbc);

        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnFoto = new JButton("Seleccionar...");
        btnFoto.addActionListener(e -> seleccionarFoto());
        lblFotoPath = new JLabel("Ninguna seleccionada");
        lblFotoPath.setFont(new Font("Arial", Font.ITALIC, 11));

        photoPanel.add(btnFoto);
        photoPanel.add(lblFotoPath);

        gbc.gridx = 1;
        formPanel.add(photoPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(40, 167, 69));
        btnGuardar.setForeground(Color.WHITE);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Foto del Estudiante");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png", "jpeg"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedPhotoFile = fileChooser.getSelectedFile();
            lblFotoPath.setText(selectedPhotoFile.getName());
        }
    }

    public void setCardId(String id) {
        lblIdTarjeta.setText(id);
    }

    public void addGuardarListener(ActionListener l) {
        btnGuardar.addActionListener(l);
    }

    public void addCancelarListener(ActionListener l) {
        btnCancelar.addActionListener(l);
    }

    public EstudianteDTO obtenerDatos() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setIdTarjeta(lblIdTarjeta.getText());
        dto.setNombre(txtNombre.getText());
        dto.setApellido(txtApellido.getText());
        dto.setCedula(txtCedula.getText());

        try {
            dto.setEdad(Integer.parseInt(txtEdad.getText()));
        } catch (NumberFormatException e) {
            dto.setEdad(0);
        }

        dto.setSexo(cmbSexo.getSelectedIndex() + 1);

        try {
            dto.setAula(Integer.parseInt(txtAula.getText()));
        } catch (NumberFormatException e) {
            dto.setAula(0);
        }

        dto.setEstado("A");

        // Handle Photo Logic (Move file to Storage)
        if (selectedPhotoFile != null && selectedPhotoFile.exists()) {
            try {
                // Create target directory if needed
                File storageDir = new File("Storage" + File.separator + "Imagenes");
                if (!storageDir.exists())
                    storageDir.mkdirs();

                // Generate unique filename: Cedula + Extension
                String ext = getFileExtension(selectedPhotoFile);
                String newFileName = dto.getCedula() + "." + ext;
                File targetFile = new File(storageDir, newFileName);

                // Copy file
                Files.copy(selectedPhotoFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Save Relative path to DTO
                dto.setFotoPath(targetFile.getPath());

            } catch (IOException e) {
                System.err.println("Error copiando imagen: " + e.getMessage());
                // Don't fail the registration, just log err
            }
        }

        return dto;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "jpg"; // default
        }
        return name.substring(lastIndexOf + 1);
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtEdad.setText("");
        txtAula.setText("");
        lblIdTarjeta.setText("---");
        selectedPhotoFile = null;
        lblFotoPath.setText("Ninguna seleccionada");
    }
}
