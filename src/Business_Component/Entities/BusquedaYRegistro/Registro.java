package Business_Component.Entities.BusquedaYRegistro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class Registro {

    public static File exportarTablaACSV(DefaultTableModel attendanceModel) throws IOException {
        // Generar nombre basado en fecha y hora
        String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String horaExport = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        String nombreArchivo = "Asistencias_" + fechaHoy + "_" + horaExport + ".csv";
        
        // Localizar escritorio y carpeta
        String rutaEscritorio = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        File carpetaDestino = new File(rutaEscritorio, "Asistencia");

        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();
        }

        File archivo = new File(carpetaDestino, nombreArchivo);

        // Escritura del CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            int colCount = attendanceModel.getColumnCount();
            int rowCount = attendanceModel.getRowCount();

            // Escribir encabezados
            StringBuilder headerLine = new StringBuilder();
            for (int col = 0; col < colCount; col++) {
                if (col > 0) headerLine.append(";");
                headerLine.append(attendanceModel.getColumnName(col));
            }
            writer.write(headerLine.toString());
            writer.newLine();

            // Escribir datos de las filas
            for (int row = 0; row < rowCount; row++) {
                StringBuilder dataLine = new StringBuilder();
                for (int col = 0; col < colCount; col++) {
                    if (col > 0) dataLine.append(";");
                    Object value = attendanceModel.getValueAt(row, col);
                    // Reemplazamos puntos y comas internos para no dañar el CSV
                    String cellValue = (value != null) ? value.toString().replace(";", ",") : "";
                    dataLine.append(cellValue);
                }
                writer.write(dataLine.toString());
                writer.newLine();
            }
        }
        return archivo;
    }

}
