package Infraestructure_Component;

import java.io.File;

public abstract class AppConfig {

    private AppConfig() {
    }

    public static final String DATABASE = "jdbc:sqlite:Storage" + File.separator + "DataBase" + File.separator
            + "asistencia_db.sqlite";
    public static final String LOGFILE = "Storage" + File.separator + "Logs" + File.separator + "AppErrors.log";
}