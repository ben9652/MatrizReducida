/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import gui.cadenas.modelos.Palabra;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class Pruebas {
    public static void main(String[] args) {
        //7-2/-5-1+i-i-2/-54i
        Palabra expresion = new Palabra("2i");
        if(expresion.esComplejo().equals(Palabra.EXITO_COMPLEJO)){
            Numero numero = new Numero(expresion);
            Long[][] numeros = numero.getNumero();
            System.out.println("Parte real: " + Numero.verFraccion(numeros[0]) +
                    "\nParte imaginaria: " + Numero.verFraccion(numeros[1]));
        }
        else System.out.println("No es un complejo.");
//        Scanner scanner = new Scanner(System.in);
//        String expresion;
//        
//        while(true){
//            System.out.print("Escriba un número complejo ('exit' para salir): ");
//            expresion = scanner.nextLine();
//            
//            if(expresion.equals("exit")) break;
//            
//            Palabra palabra = new Palabra(expresion);
//            System.out.println(palabra.esComplejo());
//        }
    }
    
    public static int strlen(Character[] cad){
        int c = 0;
        for(int i = 0;cad[i]!=null;i++)
            c++;
        return c;
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
