package Business_Component.Entities.BusquedaYRegistro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Business_Component.FactoryBL;
import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;

public class Registro {

    private FactoryBL<EstudianteDTO> factory;

    public Registro () throws AppException {
        try {

            this.factory = new FactoryBL<>(EstudianteDAO.class);

        } catch (Exception e) {

            throw new AppException(e, getClass(), "Constructor");

        }
    }

    public EstudianteDTO buscarIdTarjeta (String IdTarjeta) throws AppException {
        try {
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            for (EstudianteDTO estudianteDTO : estudianteDAO.readAll()){
                if (estudianteDTO.getIdTarjeta().equals(IdTarjeta)) return estudianteDTO;
            }
            return null;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "buscarIdTarjeta()");
        }
    }

    public void exportarDatosEstudiante (EstudianteDTO estudiante) throws AppException {
        String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String nombreArchivo = "Asistencias_" + fechaHoy + ".csv";
        
        String rutaCarpeta = System.getProperty("user.home") + File.separator + "Asistencias";
        File carpeta = new File(rutaCarpeta);
        
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(carpeta, nombreArchivo);
        boolean esNuevo = !archivo.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {

            if (esNuevo) {
                writer.write("Cedula,Nombre,Fecha,Hora,IdTarjeta");
                writer.newLine();
            }

            String horaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            String linea = String.format("%s,%s,%s,%s,%s",
                    estudiante.getCedula(), 
                    estudiante.getNombre(),
                    fechaHoy,
                    horaActual,
                    estudiante.getIdTarjeta());

            writer.write(linea);
            writer.newLine();

        } catch (IOException e) {
            throw new AppException(e, getClass(), "exportarDatosEstudiante()");
        }
    }

    public void guardarAsistencia (String IdTarjeta) throws AppException {
        try {
            EstudianteDTO estudiante = buscarIdTarjeta(IdTarjeta);
            if (estudiante != null){
                exportarDatosEstudiante(estudiante);
            }
        } catch (Exception e) {
            throw new AppException(e, getClass(), "guardarAsistencia()");
        }
    }
}
