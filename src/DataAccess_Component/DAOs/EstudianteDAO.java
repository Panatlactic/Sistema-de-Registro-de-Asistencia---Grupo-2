package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteDAO extends DataHelperSQLiteDAO <EstudianteDTO>{
    public EstudianteDAO () throws AppException{
        super(EstudianteDTO.class, "Estudiante", "IdEstudiante");
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
