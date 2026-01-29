package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.CredencialesDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para la tabla Credencial.
 */
public class CredencialesDAO extends DataHelperSQLiteDAO<CredencialesDTO> {

    public CredencialesDAO() throws AppException {
        super(CredencialesDTO.class, "Credencial", "IdCredencial");
    }

    /**
     * Valida las credenciales de un usuario.
     * @param usuario Nombre de usuario
     * @param clave Contraseña
     * @return CredencialesDTO si las credenciales son válidas, null si no
     */
    public CredencialesDTO validarCredenciales(String usuario, String clave) throws AppException {
        if (usuario == null || clave == null) {
            return null;
        }

        String sql = "SELECT * FROM Credencial WHERE Estado = 'A' AND Usuario = ? AND Clave = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.trim());
            stmt.setString(2, clave);
            ResultSet rs = stmt.executeQuery();

            CredencialesDTO resultado = null;
            if (rs.next()) {
                resultado = mapResultSetToEntity(rs);
            }

            rs.close();
            stmt.close();
            return resultado;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "validarCredenciales");
        }
    }
}
