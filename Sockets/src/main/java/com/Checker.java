package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static String check(String mensaje){

        mensaje=mensaje.replaceAll("\\{","");
        mensaje=mensaje.replaceAll("\\}","");
        String[] checkNumeroFarola=mensaje.split("farolaNumero:");
        if(checkNumeroFarola.length==2){
           String[] valoresFarola=checkNumeroFarola[1].split(",valor:");
           if(valoresFarola.length==2){
               try{
                   Integer.parseInt(valoresFarola[0]);
                   Integer.parseInt(valoresFarola[1]);
                   return "OK";
               }catch(NumberFormatException ex){
                   return "KO";
               }


           }
        }
        return "KO";
    }

    public static String checkMessageWithRegExp(String msg) {
        //  {farolaNumero:1, valor:10}
        String regexp = "\\{farolaNumero:\\d+, valor:\\d+\\}";
        Matcher matcher = Pattern.compile(regexp).matcher(msg);
        return matcher.matches()?"OK":"KO";
    }
}
