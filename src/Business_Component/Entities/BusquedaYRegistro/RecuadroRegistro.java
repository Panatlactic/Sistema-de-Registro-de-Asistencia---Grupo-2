package Business_Component.Entities.BusquedaYRegistro;

import Business_Component.Entities.EstudianteBL;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;
import Infraestructure_Component.Tools.AppMSG;
import UserInterface_Component.Panels.HomePanel;
import UserInterface_Component.Panels.HomePanel.RegistroRequestHandler;

public class RecuadroRegistro {

    EstudianteBL estudianteBL;
    HomePanel homePanel;
    private RegistroRequestHandler registroHandler;

    public void onCardRead(String codigoTarjeta) {
        if (codigoTarjeta == null || codigoTarjeta.isBlank())
            return;

        try {
            EstudianteDTO estudiante = estudianteBL.validarAcceso(codigoTarjeta.trim());

            if (estudiante != null) {

                homePanel.appendAttendanceFromEstudiante(estudiante);
                homePanel.setStatusMessage("Asistencia registrada: " + estudiante.getNombre());

            } else {

                boolean desea = AppMSG.showConfirmYesNo(
                        "La tarjeta [" + codigoTarjeta + "] no está registrada.\n" +
                                "¿Desea registrar un nuevo estudiante?");

                if (desea) {
                    if (registroHandler != null) {
                        registroHandler.onRequestRegistro(codigoTarjeta);
                        homePanel.setStatusMessage("Redirigiendo a registro para tarjeta: " + codigoTarjeta);
                    } else {
                        AppMSG.showError("No hay handler de navegación a RegistroPanel configurado.");
                    }
                } else {
                    homePanel.setStatusMessage("Tarjeta no registrada (cancelado): " + codigoTarjeta);
                }
            }

        } catch (AppException ex) {
            AppMSG.showError("Error procesando tarjeta: " + ex.getMessage());
        }
    }
}
