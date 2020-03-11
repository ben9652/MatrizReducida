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
        Long[] a = {Long.valueOf(213), Long.valueOf(-213)};
        Long[] b = {Long.valueOf(225), Long.valueOf(3)};
        
        Long[] resultado = Numero.sdf(a, b);
        System.out.println("(" + a[0] + "/" + a[1] + ") + (" + b[0] + "/" + b[1] + ") = (" + resultado[0] + "/" + resultado[1] + ")");
        //No se debería validar 7/+8/7i ni 7/+4/+5/+6/+6/+7
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
