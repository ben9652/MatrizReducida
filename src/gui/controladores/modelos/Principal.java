/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controladores.modelos;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Benjamin
 */
public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a_evaluar = "hola + MUNDao !! estoy en Argentina!!";
        String regex;
        
        while(true){
            regex = scanner.next();
            
            if(regex.equals("exit")) break;
            Pattern patron = Pattern.compile(regex);
            Matcher m = patron.matcher(a_evaluar);
            
            boolean coincidencia = m.find();
            System.out.println("¿Hay alguna coincidencia? " + coincidencia);
        }
    }
}
