package BusinessLogic.Entities;

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
    public TutorDTO validarLogin(String usuario, String clave) throws AppException {
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
            TutorDAO tutorDAO = new TutorDAO();
            TutorDTO tutorLogueado = tutorDAO.readById(credencialValidada.getDueño()); 

            return tutorLogueado;

        } catch (Exception e) {
            throw new AppException(e, getClass(), "validarLogin");
        }
    }
}