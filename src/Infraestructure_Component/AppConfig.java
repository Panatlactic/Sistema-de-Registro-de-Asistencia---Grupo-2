package Infraestructure_Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Infraestructure_Component.Tools.CMD;

public abstract class AppConfig {

    private AppConfig() {
    }
    private static final Properties props = new Properties();
   
    private static final String APP_PROPERTIES      = "src" + File.separator + "App.properties";
    private static final String DATABASE         = "db.File"     ;
    private static final String LOGFILE        = "df.logFile"  ;

    public static final String getDATABASE  (){ return getProperty( DATABASE      ); }
    public static final String getLOGFILE   (){ return getProperty( LOGFILE     ); }
    
    
    // AppMSGs
    public static final String MSG_DEFAULT_ERROR    = "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    public static final String MSG_DEFAULT_CLASS    = "undefined";
    public static final String MSG_DEFAULT_METHOD   = "undefined";

    static {
        try (InputStream appProperties = new FileInputStream(APP_PROPERTIES)) {
            props.load(appProperties);
        } catch (IOException e) {
            CMD.printlnError("ERROR al cargar ❱❱ " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        CMD.println("AppConfig ❱❱ "+ APP_PROPERTIES +"." + key + " : "+ value);
        if(value != null)
            return  value;
        else
            CMD.printlnError("ERROR ❱❱ " + APP_PROPERTIES +"." + key + " : "+ value);
        return null;
    }

}