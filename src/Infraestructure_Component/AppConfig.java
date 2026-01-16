package Infraestructure_Component;

public abstract class AppConfig {

    private AppConfig (){}
    
    public static final String DATABASE = "jdbc:sqlite:Storage\\DataBase\\asistencia_db.sqlite";
    public static final String LOGFILE  = "Storage\\Logs\\AppErrors.log"; 
}