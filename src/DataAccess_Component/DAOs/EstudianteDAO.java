package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class EstudianteDAO extends DataHelperSQLiteDAO <EstudianteDTO>{
    public EstudianteDAO () throws AppException{
        super(EstudianteDTO.class, "Estudiante", "IdEstudiante");
    }
    
}
