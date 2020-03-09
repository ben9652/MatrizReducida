/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Numero {
    private String cadena;
    private int[][] numero = new int[2][2];
    private static final List<String> TERMINOS_REALES = new ArrayList<>();
    private static final List<String> TERMINOS_COMPLEJOS = new ArrayList<>();

    public Numero(String cadena) {
        this.cadena = cadena;
    }

    public String getNumero() {
        return cadena;
    }

    public void setNumero(String cadena) {
        this.cadena = cadena;
    }
    
    /*
    Primero identificaré las 'i' que haya en la cadena. De ahí, extraeré la parte imaginaria de la cadena
    */
    private void strtonum(){
        final Caracter unidad_imaginaria = new Caracter('i');
        String cadSinEspacios = this.cadena.trim();
        Palabra expresion = new Palabra(cadSinEspacios);
        if(expresion.ocurrencias(unidad_imaginaria) == 1){
            
        }
    }
    
    public static int strlen(Character[] cad){
        int c = 0;
        for(int i = 0;cad[i]!=null;i++)
            c++;
        return c;
    }
    
    public static Integer potenciaNumero(Integer numero, Integer potencia){
        if(potencia == 0)   return 1;
        if(potencia == 1)   return numero;
        else                return numero * potenciaNumero(numero, potencia-1);
    }
    
    public static Character[] toCharacterArray(char c[]){
        Character[] nuevacadena = new Character[c.length];
        for(int i = 0;i<nuevacadena.length;i++)
            nuevacadena[i] = c[i];
        return nuevacadena;
    }
    
    public static String getCharacterString(Character[] cad){
        String cadena = cad[0].toString();
        String aux;
        for(int i = 1;i<cad.length;i++){
            aux = cad[i].toString();
            cadena = cadena.concat(aux);
        }
        return cadena;
    }
}
