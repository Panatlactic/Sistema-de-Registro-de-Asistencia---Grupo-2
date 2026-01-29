package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.TutorDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para la tabla Tutor.
 */
public class TutorDAO extends DataHelperSQLiteDAO<TutorDTO> {

    public TutorDAO() throws AppException {
        super(TutorDTO.class, "Tutor", "IdTutor");
    }

    /**
     * Busca un tutor por su IdCredencial.
     * 
     * @param idCredencial ID de la credencial asociada
     * @return TutorDTO si existe, null si no
     */
    public TutorDTO readByIdCredencial(Integer idCredencial) throws AppException {
        if (idCredencial == null) {
            return null;
        }

        String sql = "SELECT * FROM Tutor WHERE Estado = 'A' AND IdCredencial = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCredencial);
            ResultSet rs = stmt.executeQuery();

            TutorDTO resultado = null;
            if (rs.next()) {
                resultado = mapResultSetToEntity(rs);
            }

            rs.close();
            stmt.close();
            return resultado;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "readByIdCredencial");
        }
    }
}
