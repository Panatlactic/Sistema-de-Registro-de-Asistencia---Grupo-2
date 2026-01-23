package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteDAO extends DataHelperSQLiteDAO<EstudianteDTO> {
    public EstudianteDAO() throws AppException {
        super(EstudianteDTO.class, "Estudiante", "IdEstudiante");

        // AUTO-MIGRATION: Ensure FotoPath column exists
        try {
            Connection conn = openConnection();
            // Check if column exists by querying schema or attempt to add and ignore
            // duplicate error (SQLite specific)
            // Simpler approach for SQLite: Try to add it, catch error if it exists.
            try (java.sql.Statement stmt = conn.createStatement()) {
                stmt.execute("ALTER TABLE Estudiante ADD COLUMN FotoPath TEXT");
                System.out.println("[DB]: Columna FotoPath agregada exitosamente.");
            } catch (SQLException e) {
                // Column likely exists, ignore
                if (!e.getMessage().contains("duplicate column name")) {
                    System.out.println("[DB Check]: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            // Non-critical, but should be noted
            System.err.println("[DB Warning]: Could not check schema migration: " + e.getMessage());
        }
    }

    /**
     * Busca un estudiante usando el código de la tarjeta RFID.
     * Si falla algo en la BD, lanza una AppException
     */
    public EstudianteDTO readByIdTarjeta(String idTarjeta) throws AppException {
        String sql = "SELECT * FROM Estudiante WHERE Estado = 'A' AND IdTarjeta = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, idTarjeta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                EstudianteDTO resultado = mapResultSetToEntity(rs);
                rs.close();
                stmt.close();
                return resultado;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "readByIdTarjeta");
        }
        return null;
    }
public EstudianteDTO readByCedula(String cedula) throws AppException {
        String sql = "SELECT * FROM Estudiante WHERE Estado = 'A' AND Cedula = ?";
        
        try {
            Connection conn = openConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                EstudianteDTO resultado = mapResultSetToEntity(rs);
                
                rs.close();
                stmt.close();
                return resultado;
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "readByCedula");
        }
        return null; 
    }    
}
