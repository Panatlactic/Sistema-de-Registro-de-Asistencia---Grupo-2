package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.CursoDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class CursoDAO extends DataHelperSQLiteDAO <CursoDTO>{
    public CursoDAO () throws AppException {
        super(CursoDTO.class, "Curso", "IdCurso");
    }
}
