package DataAccess_Component.DAOs;

import DataAccess_Component.DTOs.CredencialesDTO;
import DataAccess_Component.Helper.DataHelperSQLiteDAO;
import Infraestructure_Component.AppException;

public class CredencialesDAO extends DataHelperSQLiteDAO <CredencialesDTO> {
    public CredencialesDAO () throws AppException {
        super(CredencialesDTO.class, "Credencial", "IdCredencial");
    }
}
