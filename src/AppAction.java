import Business_Component.Entities.LoginTutorBL;
import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.EstudianteDTO;
import DataAccess_Component.DTOs.TutorDTO;
import Infraestructure_Component.AppException;

public class AppAction {
    
    public boolean ingresar (String usuario, String clave) throws AppException {
        try {

            TutorDTO tutorLogin = new LoginTutorBL().validarLogin(usuario, clave);
            if (tutorLogin != null){
                return true;
            }

        } catch (Exception e) {
            throw new AppException(e, getClass(), "ingresar()");
        }

        return false;
    }

    public boolean registrarEstudiante (EstudianteDTO estudianteNuevo) throws AppException {
        try {
            

            
        } catch (Exception e) {
            throw new AppException(e, getClass(), "registrarEstudiante()");
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
