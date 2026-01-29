package Business_Component.Entities;

import DataAccess_Component.DAOs.CredencialesDAO;
import DataAccess_Component.DAOs.TutorDAO;
import DataAccess_Component.DTOs.CredencialesDTO;
import DataAccess_Component.DTOs.TutorDTO;
import Infraestructure_Component.AppException;

/**
 * Lógica de negocio para el login de tutores.
 */
public class LoginTutorBL {

    /**
     * Valida las credenciales de un tutor.
     * 
     * @param usuario Nombre de usuario
     * @param clave Contraseña
     * @return TutorDTO si las credenciales son válidas, null si no
     */
    public TutorDTO validarLogin(String usuario, String clave) throws AppException {
        // Validar entrada
        if (usuario == null || usuario.trim().isEmpty()) {
            return null;
        }
        if (clave == null || clave.isEmpty()) {
            return null;
        }

        try {
            // 1. Validar credenciales
            CredencialesDAO credDAO = new CredencialesDAO();
            CredencialesDTO credencial = credDAO.validarCredenciales(usuario.trim(), clave);

            if (credencial == null) {
                return null; // Credenciales inválidas
            }

            // 2. Buscar el tutor asociado a esa credencial
            TutorDAO tutorDAO = new TutorDAO();
            TutorDTO tutor = tutorDAO.readByIdCredencial(credencial.getIdCredencial());

            return tutor; // Puede ser null si no hay tutor asociado

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "validarLogin");
        }
    }
}