package DataAccess.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.SexoDTO;
import DataAccess.Helper.DataHelperSQLite;
import DataAccess.Helper.IDAO;
import Framework.GTwoException;

public class SexoDAO extends DataHelperSQLite implements IDAO <SexoDTO> {

    @Override
    public boolean create(SexoDTO entity) throws Exception {
        String query = "INSERT INTO Sexo (Descripcion) VALUES (?)";
        try {
            
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getDescripcion());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public SexoDTO readBy(Integer id) throws Exception {
        SexoDTO sexoDTO = new SexoDTO();
        String query = " SELECT PkSexo, Descripcion "
                     + " FROM Sexo "
                     + " AND PkSexo = " + id.toString();

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                sexoDTO = new SexoDTO(rs.getInt(1), rs.getString(2));
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readBy()");
        }

        return sexoDTO;
    }

    @Override
    public List<SexoDTO> readAll() throws Exception {
        
        List <SexoDTO> lst = new ArrayList<>();
        String query = " SELECT PkSexo, Descripcion "
                     + " FROM Sexo ";

        try {

            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                SexoDTO sexoDTO = new SexoDTO(rs.getInt(1), rs.getString(2));
                lst.add(sexoDTO);
            }
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;

    }

    @Override
    public boolean update(SexoDTO entity) throws Exception {

        String query = "UPDATE Sexo SET PkSexo = ?, Descripcion = ?";
        try {

            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getPkSexo());
            pstmt.setString(2, entity.getDescripcion());
            pstmt.executeUpdate();
            return true;
            
        } catch (Exception e) {
            throw new GTwoException(e.getMessage(), getClass().getName(), "update()");
        }
        
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        
        String query = "UPDATE Sexo SET Descripcion = ? WHERE PkSexo = ?";
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
        
        String query = "SELECT COUNT(*) TotalReg FROM Sexo";
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
