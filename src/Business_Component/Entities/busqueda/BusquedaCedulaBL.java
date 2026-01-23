package Business_Component.Entities.busqueda;

import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;


public class BusquedaCedulaBL {
    
    public BusquedaCedulaBL() {
    }

    public boolean existeEstudiante(String cedula) throws AppException {
        EstudianteDTO dto = buscarPorCedula(cedula);
        return dto != null;
    }

    public EstudianteDTO buscarPorCedula(String cedula) throws AppException {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null; 
        }

        try {
            EstudianteDAO dao = new EstudianteDAO();
            return dao.readByCedula(cedula.trim()); 
        } catch (AppException e) {
            throw e;
        }
    }
}