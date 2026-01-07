package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.CursoDTO;
import DataAccess.Helper.DataHelperSQLite;
import DataAccess.Helper.IDAO;
import Framework.GTwoException;

public class CursoDAO extends DataHelperSQLite implements IDAO <CursoDTO>  {

    @Override
    public boolean create(CursoDTO entity) throws Exception {
        String query = "INSERT INTO Curso (NombreGrado, DocenteTutor) VALUES (?, ?)";
        try {
            
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreGrado());
            pstmt.setInt(2, entity.getDocenteTutor());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public CursoDTO readBy(Integer id) throws Exception {
        CursoDTO cursoDTO = new CursoDTO();
        String query = " SELECT PkCurso, NombreGrado, DocenteTutor "
                     + " FROM Curso "
                     + " AND PkCurso = " + id.toString();

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                cursoDTO = new CursoDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readBy()");
        }

        return cursoDTO;
    }

    @Override
    public List<CursoDTO> readAll() throws Exception {
        
        List <CursoDTO> lst = new ArrayList<>();
        String query = " SELECT PkCurso, NombreGrado, DocenteTutor "
                     + " FROM Curso ";

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                CursoDTO cursoDTO = new CursoDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));
                lst.add(cursoDTO);
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;

    }

    @Override
    public boolean update(CursoDTO entity) throws Exception {

        String query = "UPDATE Curso SET PkCurso = ?, NombreGrado = ?, DocenteTutor = ?";
        try {

            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getPkCurso());
            pstmt.setString(2, entity.getNombreGrado());
            pstmt.setInt(3, entity.getDocenteTutor());
            pstmt.executeUpdate();
            return true;
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "update()");
        }
        
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        
        String query = "DELETE FROM Curso WHERE PkCurso = ?"; 
        try {

            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "delete()");
        }
        
    }

    @Override
    public Integer getMaxReg() throws Exception {
        
        String query = "SELECT COUNT(*) TotalReg FROM Curso";
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
