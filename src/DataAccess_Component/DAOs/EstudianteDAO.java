package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para la entidad Estudiante.
 * Provee operaciones CRUD y búsquedas especializadas.
 */
public class EstudianteDAO extends DataHelperSQLiteDAO<EstudianteDTO> {
    
    public EstudianteDAO() throws AppException {
        super(EstudianteDTO.class, "Estudiante", "IdEstudiante");
    }

    /**
     * Busca un estudiante usando el código de la tarjeta RFID.
     * Solo retorna estudiantes activos (Estado = 'A').
     * 
     * @param idTarjeta Código de la tarjeta RFID
     * @return EstudianteDTO si existe, null si no se encuentra
     * @throws AppException Si ocurre un error en la BD
     */
    public EstudianteDTO readByIdTarjeta(String idTarjeta) throws AppException {
        if (idTarjeta == null || idTarjeta.trim().isEmpty()) {
            return null;
        }
        
        String sql = "SELECT * FROM Estudiante WHERE Estado = 'A' AND IdTarjeta = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTarjeta.trim());
            ResultSet rs = stmt.executeQuery();

            EstudianteDTO resultado = null;
            if (rs.next()) {
                resultado = mapResultSetToEntity(rs);
            }
            
            rs.close();
            stmt.close();
            return resultado;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "readByIdTarjeta");
        }
    }

    /**
     * Busca un estudiante por su número de cédula.
     * Solo retorna estudiantes activos (Estado = 'A').
     * 
     * @param cedula Número de cédula del estudiante
     * @return EstudianteDTO si existe, null si no se encuentra
     * @throws AppException Si ocurre un error en la BD
     */
    public EstudianteDTO readByCedula(String cedula) throws AppException {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        
        String sql = "SELECT * FROM Estudiante WHERE Estado = 'A' AND Cedula = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cedula.trim());
            ResultSet rs = stmt.executeQuery();

            EstudianteDTO resultado = null;
            if (rs.next()) {
                resultado = mapResultSetToEntity(rs);
            }

            rs.close();
            stmt.close();
            return resultado;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "readByCedula");
        }
    }

    /**
     * Verifica si ya existe una tarjeta RFID registrada.
     * 
     * @param idTarjeta Código de la tarjeta RFID
     * @return true si la tarjeta ya está registrada
     * @throws AppException Si ocurre un error en la BD
     */
    public boolean existeTarjeta(String idTarjeta) throws AppException {
        return readByIdTarjeta(idTarjeta) != null;
    }

    /**
     * Verifica si ya existe una cédula registrada.
     * 
     * @param cedula Número de cédula
     * @return true si la cédula ya está registrada
     * @throws AppException Si ocurre un error en la BD
     */
    public boolean existeCedula(String cedula) throws AppException {
        return readByCedula(cedula) != null;
    }
}
