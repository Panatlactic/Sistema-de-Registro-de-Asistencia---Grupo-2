package UserInterface_Component.Actions;

import Business_Component.Entities.LoginTutorBL;
import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.CredencialesDTO;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;

public class AppAction {
    
    public boolean ingresar (String usuario, String clave) throws AppException {
        try {

            CredencialesDTO credencialesDTO = new LoginTutorBL().validarLogin(usuario, clave);
            if (credencialesDTO != null){
                return true;
            }

        } catch (Exception e) {
            throw new AppException(e, getClass(), "ingresar()");
        }

        return false;
    }

    public boolean eliminiarEstudiante (String cedula) throws AppException {
        try {

            EstudianteDAO estudianteDAO = new EstudianteDAO();
            EstudianteDTO estudiante_borrar = estudianteDAO.readByCedula(cedula);

            if (estudiante_borrar == null) return false;
            if (estudianteDAO.delete(estudiante_borrar.getIdEstudiante())) return true;
            
            return false;

        } catch (Exception e) {
            throw new AppException(e, getClass(), "eliminarEstudiante()");
        }

    }


}
