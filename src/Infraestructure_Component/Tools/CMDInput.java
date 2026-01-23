package Infraestructure_Component.Tools;

import java.util.Scanner;

public class CMDInput {

    // AÑADIR CE_

    static Scanner ce_sc = new Scanner(System.in);
    private CMDInput(){}

    public static int getNumeroPositivo(String ce_etiqueta, String ce_errorMsg){
        int n =-1;
        String str;
        do{
            System.out.print(ce_etiqueta);
            str =  ce_sc.next();
            try {
                n = Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println(ce_errorMsg);
            }
        }while(n<0);
        return n;
    }
    
    public static String getCaracteres(String etiqueta)
    {
        String str="";
        do {
            System.out.print(etiqueta);
            str =  ce_sc.next().trim();
            if (str.trim().equals(""))
                System.out.println(" :( Valor no valido... !");
        } while (str.isEmpty());
        return str;
    }

    public static void pressKey()
    {
        System.out.print("\n\nPress any key..");
        try{
            System.in.read();
        }catch(Exception e){
            System.out.println("");
        }
    }
}
