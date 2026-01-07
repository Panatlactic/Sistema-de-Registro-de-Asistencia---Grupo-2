package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.TutorDTO;
import DataAccess.Helper.DataHelperSQLite;
import DataAccess.Helper.IDAO;
import Framework.GTwoException;

public class TutorDAO extends DataHelperSQLite implements IDAO <TutorDTO> {
    
    @Override
    public boolean create(TutorDTO entity) throws Exception {
        String query = "INSERT INTO Tutor (NombreTutor) VALUES (?)";
        try {
            
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreTutor());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public TutorDTO readBy(Integer id) throws Exception {
        TutorDTO tutorDTO = new TutorDTO();
        String query = " SELECT PkTutor, NombreTutor "
                     + " FROM Tutor "
                     + " AND PkTutor = " + id.toString();

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                tutorDTO = new TutorDTO(rs.getInt(1), rs.getString(2));
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readBy()");
        }

        return tutorDTO;
    }

    @Override
    public List<TutorDTO> readAll() throws Exception {
        
        List <TutorDTO> lst = new ArrayList<>();
        String query = " SELECT PkTutor, NombreTutor "
                     + " FROM Tutor ";

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                TutorDTO tutorDTO = new TutorDTO(rs.getInt(1), rs.getString(2));
                lst.add(tutorDTO);
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;

    }

    @Override
    public boolean update(TutorDTO entity) throws Exception {

        String query = "UPDATE Tutor SET PkTutor = ?, NombreTutor = ?";
        try {

            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getPkTutor());
            pstmt.setString(2, entity.getNombreTutor());
            pstmt.executeUpdate();
            return true;
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "update()");
        }
        
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        
        String query = "UPDATE Tutor SET NombreTutor = ? WHERE PkTutor = ?";
        try {
            
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "delete()");
        }
        
    }

    @Override
    public Integer getMaxReg() throws Exception {
        
        String query = "SELECT COUNT(*) TotalReg FROM Tutor";
        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "getMaxReg()");
        }

        return 0;
    }

}
