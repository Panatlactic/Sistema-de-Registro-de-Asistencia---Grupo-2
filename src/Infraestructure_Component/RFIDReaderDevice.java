package Infraestructure_Component;

import com.fazecast.jSerialComm.SerialPort;

import Business_Component.Entities.BusquedaYRegistro.Registro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import Infraestructure_Component.Interfaces_Component.RFIDListener;


public class RFIDReaderDevice implements Runnable {
    
    private SerialPort        puertoArduino   ;     
    private RFIDListener      listener        ; 
    private volatile boolean  activo = false  ; 
    private String            puertoNombre    ;

   
    public RFIDReaderDevice(String puertoNombre, RFIDListener listener) {
        this.puertoNombre = puertoNombre      ;    
        this.listener = listener              ;
    }

    /**
     * Intenta abrir el puerto
     */
    public void start() throws AppException {
        puertoArduino = SerialPort.getCommPort(puertoNombre);
        puertoArduino.setBaudRate(9600);
        puertoArduino.setNumDataBits(8);
        puertoArduino.setNumStopBits(1);
        puertoArduino.setParity(SerialPort.NO_PARITY);
        puertoArduino.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2000, 0);

        if (puertoArduino.openPort()) {
            System.out.println("[HARDWARE]: Puerto " + puertoNombre + " abierto. Esperando tarjetas...");
            this.activo = true;
            
            Thread hiloLector = new Thread(this);
            hiloLector.setDaemon(true);
            hiloLector.start(); 
        } else {
          
            throw new AppException("No se pudo abrir el puerto " + puertoNombre + ". Verifica que el Arduino esté conectado.");
        }
    }

    public void stop() {
        activo = false;
        if (puertoArduino != null) {
            puertoArduino.closePort();
        }
    }

    @Override
    public void run(){
        BufferedReader reader = null;

        // se instancio registro como nulo
        Registro registro = null;
        try {
            reader = new BufferedReader(new InputStreamReader(puertoArduino.getInputStream()));

            // se instanció registro
            registro = new Registro();
            while (activo) {
                try {
                    if (reader.ready()) {
                        String linea = reader.readLine();
                        
                        if (linea != null && linea.contains("UID")) {
                            String codigoLimpio = linea.replace("UID de la tarjeta:", "").trim();
                            
                            if (listener != null && !codigoLimpio.isEmpty()) {
                              
                                final String codigo = codigoLimpio;
                                final Registro registroFinal = registro;

                                new Thread(() -> {
                                    try {
                                        listener.onCardRead(codigo);

                                        // if para guardar la asistencia en el registro
                                        if (registroFinal != null) registroFinal.guardarAsistencia(codigo);

                                    } catch (Exception e) {
                                        System.err.println("[ERROR CALLBACK]: " + e.getMessage());
                                    }
                                }).start();
                            }
                        }
                    } else {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    if (activo) {
                        System.err.println("[HARDWARE]: Error de lectura: " + e.getMessage());
                        Thread.sleep(500);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[HARDWARE]: Error fatal en el lector: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (Exception ignored) {}
        }
        System.out.println("[HARDWARE]: Lector detenido.");
    }
}