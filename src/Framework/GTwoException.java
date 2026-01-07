package Framework;

import java.util.ArrayList;

public class GTwoException extends Exception {

    private static ArrayList <String> Errores = new ArrayList<>();

    public GTwoException (String e, String clase, String metodo){
        System.out.println("Se ha sucitado un error inesperado...");
        Errores.add(añadirErr(e, clase, metodo));
    }

    public String añadirErr (String e, String clase, String metodo){
        String err = clase + "." + metodo + "." + e;
        return err;
    }
    
}
