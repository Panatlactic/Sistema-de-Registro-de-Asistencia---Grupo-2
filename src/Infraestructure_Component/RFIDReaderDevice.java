package Infraestructure_Component;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import Infraestructure_Component.Interfaces_Component.RFIDListener;

public class RFIDReaderDevice {

    private SerialPort puertoArduino;
    private RFIDListener listener;
    private volatile boolean activo = false;
    private String puertoNombre;
    private StringBuilder buffer = new StringBuilder();

    public RFIDReaderDevice(String puertoNombre, RFIDListener listener) {
        this.puertoNombre = puertoNombre;
        this.listener = listener;
    }

    /**
     * Intenta abrir el puerto usando eventos (no bloquea)
     */
    public void start() throws AppException {
        puertoArduino = SerialPort.getCommPort(puertoNombre);
        puertoArduino.setBaudRate(9600);
        puertoArduino.setNumDataBits(8);
        puertoArduino.setNumStopBits(1);
        puertoArduino.setParity(SerialPort.NO_PARITY);
        
        // Usar timeout no bloqueante
        puertoArduino.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        if (puertoArduino.openPort()) {
            System.out.println("[HARDWARE]: Puerto " + puertoNombre + " abierto. Esperando tarjetas...");
            this.activo = true;

            // Usar listener de eventos en lugar de hilo bloqueante
            puertoArduino.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (!activo) return;
                    
                    if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                        try {
                            int bytesAvailable = puertoArduino.bytesAvailable();
                            if (bytesAvailable > 0) {
                                byte[] readBuffer = new byte[bytesAvailable];
                                puertoArduino.readBytes(readBuffer, bytesAvailable);
                                String data = new String(readBuffer);
                                
                                // Acumular en buffer
                                buffer.append(data);
                                
                                // Procesar líneas completas
                                String content = buffer.toString();
                                int newlineIndex;
                                while ((newlineIndex = content.indexOf('\n')) >= 0) {
                                    String linea = content.substring(0, newlineIndex).trim();
                                    content = content.substring(newlineIndex + 1);
                                    
                                    if (linea.contains("UID")) {
                                        String codigoLimpio = linea.replace("UID de la tarjeta:", "").trim();
                                        if (listener != null && !codigoLimpio.isEmpty()) {
                                            System.out.println("[HARDWARE]: Tarjeta detectada: " + codigoLimpio);
                                            listener.onCardRead(codigoLimpio);
                                        }
                                    }
                                }
                                buffer = new StringBuilder(content);
                            }
                        } catch (Exception e) {
                            System.err.println("[HARDWARE]: Error procesando datos: " + e.getMessage());
                        }
                    }
                }
            });
            
            System.out.println("[HARDWARE]: Listener de eventos configurado.");
        } else {
            throw new AppException(
                    "No se pudo abrir el puerto " + puertoNombre + ". Verifica que el Arduino esté conectado.");
        }
    }

    public void stop() {
        activo = false;
        if (puertoArduino != null) {
            puertoArduino.removeDataListener();
            puertoArduino.closePort();
        }
        System.out.println("[HARDWARE]: Lector detenido.");
    }
}
