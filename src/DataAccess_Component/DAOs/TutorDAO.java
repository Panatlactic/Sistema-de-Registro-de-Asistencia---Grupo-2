package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.TutorDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class TutorDAO extends DataHelperSQLiteDAO <TutorDTO> {
    public TutorDAO () throws AppException{
        super(TutorDTO.class, "Tutor", "IdTutor");
    }
public TutorDTO readById(int idTutor) throws AppException {
    String sql = "SELECT * FROM Tutor WHERE IdTutor = ?";
    try {
        Connection conn = openConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idTutor);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            TutorDTO t = new TutorDTO();
            t.setIdTutor(rs.getInt("IdTutor"));
            t.setNombreTutor(rs.getString("NombreTutor")); 
            return t;
        }
    } catch (SQLException e) {
        throw new AppException(e, getClass(), "readById");
    }
    return null;
}

}
