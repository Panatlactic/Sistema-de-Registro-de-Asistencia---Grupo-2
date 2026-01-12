package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.TutorDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class TutorDAO extends DataHelperSQLiteDAO <TutorDTO> {
    public TutorDAO () throws AppException{
        super(TutorDTO.class, "Tutor", "IdTutor");
    }
}
