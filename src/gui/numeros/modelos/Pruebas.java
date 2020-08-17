/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import gui.matrices.modelos.Matriz;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Pruebas {
    public static void main(String[] args) {
        Matriz<Integer> matriz = new Matriz();
        Integer a = 9;
        Integer b = 5;
        Integer c = 1;
        Integer d = 0;
        Integer e = 7;
        Integer f = 8;
        Integer g = 4;
        Integer h = 2;
        Integer i = 6;
        
        List<Integer> fila1 = new ArrayList<>();
        fila1.add(a);
        fila1.add(b);
        fila1.add(c);
        List<Integer> fila2 = new ArrayList<>();
        fila2.add(d);
        fila2.add(e);
        fila2.add(f);
        List<Integer> fila3 = new ArrayList<>();
        fila3.add(g);
        fila3.add(h);
        fila3.add(i);
        
        matriz.addRow(fila1);
        matriz.addRow(fila2);
        matriz.addRow(fila3);
        
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
