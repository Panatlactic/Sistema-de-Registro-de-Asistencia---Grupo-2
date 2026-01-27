package Business_Component.Entities;

import DataAccess_Component.DAOs.CredencialesDAO;
import DataAccess_Component.DAOs.TutorDAO;
import DataAccess_Component.DTOs.CredencialesDTO;
import DataAccess_Component.DTOs.TutorDTO;
import Infraestructure_Component.AppException;

public class LoginTutorBL {

    /**
     * Login.
     * @param usuario
     * @param clave 
     * @return 
     * @return 
     */
    public CredencialesDTO validarLogin(String usuario, String clave) throws AppException {
        try {
            
            CredencialesDAO credDAO = new CredencialesDAO();
   
            CredencialesDTO credencialValidada = null;
            
            for (CredencialesDTO cred : credDAO.readAll()) {
                if (cred.getUsuario().equals(usuario) && cred.getClave().equals(clave)) {
                    credencialValidada = cred;
                    break; 
                }
            }
            if (credencialValidada == null) {
                return null;
            }

            return credencialValidada;

        } catch (Exception e) {
            throw new AppException(e, getClass(), "validarLogin()");
        }
    }
}