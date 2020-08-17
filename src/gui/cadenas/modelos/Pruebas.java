/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadenas.modelos;

import java.util.Scanner;

import gui.numeros.modelos.Numero;

/**
 *
 * @author Benjamin
 */
public class Pruebas {
    public static void main(String[] args) {
        
        while(true) {
            System.out.print("Escribe la expresión: ");
            Scanner scn = new Scanner(System.in);

            Palabra expresion = new Palabra(scn.nextLine());

            System.out.println("");
            String resultado = expresion.esComplejo();

            if(resultado.equals(Palabra.EXITO_COMPLEJO)) {
                Numero numero = new Numero(expresion);
                System.out.println(numero.verComplejo());
            }
            else
                System.out.println(resultado);
            
            System.out.println("\n");
        }
    }
}
