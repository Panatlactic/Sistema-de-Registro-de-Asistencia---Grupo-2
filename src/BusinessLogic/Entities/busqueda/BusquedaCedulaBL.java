package BussinesLogic.Entities.busqueda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BussinesLogic.Entities.usuario.Usuario;
import BussinesLogic.FactoryBL;
import DataAccess_Component.DAOs.EstudianteDAO;
import DataAccess_Component.DTOs.EstudianteDTODTO;
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