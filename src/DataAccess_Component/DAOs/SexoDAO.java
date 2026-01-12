package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.SexoDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class SexoDAO extends DataHelperSQLiteDAO <SexoDTO>{
    public SexoDAO () throws AppException{
        super(SexoDTO.class, "Sexo", "IdSexo");
    }
    
}
