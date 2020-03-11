/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import gui.cadenas.modelos.Palabra;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Benjamin
 */
public class Numero {
    private Palabra cadena;
    /*
        Los números complejos serán representados con un arreglo bidimensional:
     - el primer índice servirá para acceder a la parte real o compleja (0, para la real; 1, para la compleja)
     - el segundo índice servirá para acceder al numerador o denominador del número (0, para el numerador; 1, para el denominador)
    */
    private Long[][] numero = new Long[2][2];
    private static final List<String> TERMINOS_REALES = new ArrayList<>();
    private static final List<String> TERMINOS_COMPLEJOS = new ArrayList<>();

    public Numero(String cadena) {
        this.cadena = new Palabra(cadena);
    }

    public Long[][] getNumero() {
        return this.numero;
    }

    public void setNumero(Long[][] numero) {
        this.numero = numero;
    }
    
//    /*
//    Primero identificaré las 'i' que haya en la cadena. De ahí, extraeré la parte imaginaria de la cadena
//    */
//    public void strtonum(){
//        final Caracter unidad_imaginaria = new Caracter('i');
//        String cadSinEspacios = this.cadena.trim();
//        Palabra expresion = new Palabra(cadSinEspacios);
//        if(expresion.ocurrencias(unidad_imaginaria) == 1){
//            
//        }
//    }
    
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
    
    public static Long[] sdf(Long n1[],Long n2[]){
        if(n1 == null || n2 == null){
            Long[] nulo = {Long.valueOf(0), Long.valueOf(0)};
            return nulo;
        }
        
        n1 = simplificacion(n1);
        n2 = simplificacion(n2);
        
	Long[] nueva = new Long[2];
	if(!Objects.equals(n1[1], n2[1])){
            if(sonDivisibles(n1[1], n2[1]) || sonDivisibles(n2[1], n1[1])){
                if(sonDivisibles(n1[1], n2[1])){
                    nueva[0] = n1[0] + n1[1] / n2[1] * n2[0];
                    nueva[1] = n1[1];
                }
                else{
                    nueva[0] = n2[1] / n1[1] * n1[0] + n2[0];
                    nueva[1] = n2[1];
                }
            }
            else{
                nueva[1] = n1[1] * n2[1];
                nueva[0] = n2[1] * n1[0] + n1[1] * n2[0];
            }
	}
        else{
            nueva[0] = n1[0] + n2[0];
            nueva[1] = n1[1];
        }
        
        return simplificacion(nueva);
    }
    
    private static boolean sonDivisibles(Long a, Long b){
        return a%b == 0;
    }
    
    private static Long[] simplificacion(Long n[]){
        if(n == null){
            Long[] nulo = {Long.valueOf(0), Long.valueOf(0)};
            return nulo;
        }
        
        if(n[1]<0){
            n[0] = n[0] * (-1);
            n[1] = n[1] * (-1);
        }
        
        if(n[1].equals(n[0])){
            n[0] = Long.valueOf(1);
            n[1] = Long.valueOf(1);
            return n;
        }
        if(n[1].equals(n[0] * (-1))){
            n[0] = Long.valueOf(-1);
            n[1] = Long.valueOf(1);
            return n;
        }
        
        Long[] positivos = new Long[2];
        if(n[0]<0) positivos[0] = n[0] * (-1);
        else positivos[0] = n[0];
        if(n[1]<0) positivos[1] = n[1] * (-1);
        else positivos[1] = n[1];
        
        Long i;
        if(positivos[0]<positivos[1]){
            for(i = positivos[0] ; i!=0 ; i--){
                if(positivos[0] % i == 0 && positivos[1] % i == 0)
                    break;
            }
            n[0] = n[0]/i;
            n[1] = n[1]/i;
        }
        else{
            for(i = positivos[1] ; i!=0 ; i--){
                if(positivos[0] % i == 0 && positivos[1] % i == 0)
                    break;
            }
            n[0] = n[0]/i;
            n[1] = n[1]/i;
        }
        
        return n;
    }
}
