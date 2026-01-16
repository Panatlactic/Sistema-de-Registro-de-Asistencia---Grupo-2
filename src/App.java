import BusinessLogic.Entities.EstudianteBL;
import DataAccess_Component.DTOs.EstudianteDTO;
import Infraestructure_Component.AppException;
import Infraestructure_Component.RFIDReaderDevice;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static boolean modoRegistroActivo = false;    
    private static final Scanner tl = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("   SISTEMA DE ASISTENCIA RFID - CONSOLA   ");
        try {
           
            EstudianteBL logicaNegocio = new EstudianteBL();

            RFIDReaderDevice lector = new RFIDReaderDevice("COM3", (codigoLeido) -> {
                
                if (modoRegistroActivo) return; 

                System.out.println("\n-----------------------------------------");
                System.out.println("TARJETA DETECTADA: [" + codigoLeido + "]");

                try {
        
                    EstudianteDTO estudiante = logicaNegocio.validarAcceso(codigoLeido);

                    if (estudiante != null) {
                       
                        System.out.println("BIENVENIDO/A: " + estudiante.getNombre() + " " + estudiante.getApellido());
                        System.out.println("Estado: " + estudiante.getEstado());

                    } else {
                        
                        modoRegistroActivo = true; 
                        
                        System.out.println("TARJETA NO REGISTRADA.");
                        System.out.println(">> INICIANDO PROTOCOLO DE REGISTRO...");

                        System.out.print("   Ingrese NOMBRE: ");
                        String nombre = tl.nextLine();

                        System.out.print("   Ingrese APELLIDO: ");
                        String apellido = tl.nextLine();

                        
                        EstudianteDTO nuevoEst = new EstudianteDTO();
                        nuevoEst.setNombre(nombre);
                        nuevoEst.setApellido(apellido);
                        nuevoEst.setIdTarjeta(codigoLeido); 
                        nuevoEst.setEstado("A");
                       
                        nuevoEst.setEdad(0); 
                        nuevoEst.setSexo(0); 
                        nuevoEst.setAula(0);

                        if (logicaNegocio.agregarEstudiante(nuevoEst)) {
                            System.out.println("REGISTRO EXITOSO. El estudiante ha sido guardado.");
                        } else {
                            System.err.println("Error al guardar en la Base de Datos.");
                        }
                        
                        modoRegistroActivo = false; 
                        System.out.println(">> Sistema listo para la siguiente tarjeta.");
                    }

                } catch (AppException e) {
                    System.err.println("Error lógico: " + e.getMessage());
                }
            });

            
            lector.start(); 
            System.out.println(">> Lector activado y escuchando... (Presiona Ctrl+C para salir)");

          
            Object lock = new Object();
            synchronized (lock) {
                lock.wait(); 
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.WARNING, "Sistema interrumpido", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERROR FATAL EN EL SISTEMA", e);
        } finally {
            tl.close();
        }
    }
}