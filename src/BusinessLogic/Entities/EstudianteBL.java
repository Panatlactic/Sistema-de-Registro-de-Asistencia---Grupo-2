package BusinessLogic.Entities;

import BusinessLogic.FactoryBL;
import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;
import java.util.List;

public class EstudianteBL {
    private FactoryBL<EstudianteDTO> factory;

    public EstudianteBL() throws AppException {
        try {
          
            this.factory = new FactoryBL<>(EstudianteDAO.class);
        } catch (Exception e) {
            
            throw new AppException(e, getClass(), "Constructor");
        }
    }

    /**
     * MÉTODO ESPECIAL: Validar Acceso por RFID
     * 
     */
    public EstudianteDTO validarAcceso(String codigoLector) throws AppException {
     
        if (codigoLector == null || codigoLector.isEmpty()) {
            return null;
        }

        try {
         
            EstudianteDAO dao = new EstudianteDAO();
            
            return dao.readByIdTarjeta(codigoLector.trim());

        } catch (AppException e) {
            throw e;
        }
    }


   
    public boolean agregarEstudiante(EstudianteDTO estudiante) throws AppException {
        return factory.add(estudiante);
    }

    
    public List<EstudianteDTO> obtenerTodos() throws AppException {
        return factory.getAll();
    }

    
    public boolean actualizarEstudiante(EstudianteDTO estudiante) throws AppException {
        return factory.upd(estudiante);
    }
    
   
    public boolean eliminarEstudiante(Integer idEstudiante) throws AppException {
        return factory.del(idEstudiante);
    }
}