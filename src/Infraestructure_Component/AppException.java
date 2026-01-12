package Infraestructure_Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Infraestructure_Component.Tools.CMDColor;

public class AppException extends Exception {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 
    public AppException(String message) {
        super(message);
        CEsaveLogFile(null, null, message);
    }
    public AppException(Exception e, Class<?> clase, String metodo) {
        super(e.getMessage());
        CEsaveLogFile(e.getMessage(), clase, metodo);
    }
    private void CEsaveLogFile(String error, Class<?> clase, String metodo) {
        String timestamp  = LocalDateTime.now().format(FORMATTER);
        String className  = (clase == null) ? "undefined" : clase.getSimpleName();
        String methodName = (metodo == null || metodo.isBlank()) ? "undefined" : metodo;
        String logMessage = String.format("[ Grupo 2_Exception | %s.%s | %s ]  ❱ %s", className, methodName, timestamp, error);

        try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.LOGFILE, true))) {
            System.err.println(CMDColor.BLUE  + logMessage);
            writer.println(logMessage);
        } catch (Exception e) {
            System.err.println(CMDColor.RED  + "[AppException.saveLogFile] ❱ " + e.getMessage());
        }finally {
            System.out.println(CMDColor.RESET);
        }
    }
}
