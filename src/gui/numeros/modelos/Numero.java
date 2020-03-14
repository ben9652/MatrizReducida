/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import gui.cadenas.modelos.Caracter;
import gui.cadenas.modelos.Palabra;
import java.util.List;
import java.util.ArrayList;

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
    
    private List<Fraccion> terminos_reales = new ArrayList<>();
    private List<Fraccion> terminos_imaginarios = new ArrayList<>();

    public Numero(Palabra cadena) {
        this.cadena = cadena;
        this.strtonum();
        this.setNumero();
    }
    
    public Numero(Numero numero){
        this.numero = numero.getNumero();
    }

    public Long[][] getNumero() {
        return this.numero;
    }

    private void setNumero() {
        Fraccion numeroReal = new Fraccion();
        for(Fraccion n : this.terminos_reales)
            numeroReal.sdf(n);
        
        Fraccion numeroImaginario = new Fraccion();
        for(Fraccion n : this.terminos_imaginarios)
            numeroImaginario.sdf(n);
        
        this.numero[0] = numeroReal.getNumero();
        this.numero[1] = numeroImaginario.getNumero();
    }
    
    private void strtonum(){
        this.agregadoDeNumeros(this.cadena.verTerminosReales(), false);
        this.agregadoDeNumeros(this.cadena.verTerminosImaginarios(), true);
    }
    
    private void agregadoDeNumeros(List<Palabra> terminos, boolean imaginarios){
        
        for (Palabra p : terminos) {
            
            Fraccion candidato = new Fraccion();
            
            if(p.ocurrencias('/') > 0){
                String[] num = p.getPalabra().split("/");
                candidato.setNumerador(Caracter.atoi(num[0]));
                candidato.setDenominador(Caracter.atoi(num[1]));
            }
            else
                candidato.setNumerador(Caracter.atoi(p.getPalabra()));
            
            if(imaginarios == false)
                this.terminos_reales.add(candidato);
            else
                this.terminos_imaginarios.add(candidato);
        }
    }
    
    public static int strlen(Character[] cad){
        int c = 0;
        for(int i = 0;cad[i]!=null;i++)
            c++;
        return c;
    }
    
    public static Long potenciaNumero(Long numero, Integer potencia){
        if(potencia == 0)   return Long.valueOf(1);
        if(potencia == 1)   return numero;
        else                return numero * potenciaNumero(numero, potencia-1);
    }
    
    public String verComplejo(){
        Long[][] n = this.numero;
        Long[] auxiliar = new Long[2];
        String cadena = "";
        
        if(n[0][0] == 0 && n[1][0] == 0)
            return "0";
        if(n[0][0] != 0 && n[1][0] != 0){
            for(int c = 0 ; c<=1 ; c++){
                if(n[c][1] == 1){
                    if(c == 1){
                        if(n[c][0] > 0){
                            if(n[c][0] == 1)
                                cadena = cadena + " + " + Palabra.UNIDAD_IMAGINARIA;
                            else
                                cadena = cadena + " + " + n[c][0] + Palabra.UNIDAD_IMAGINARIA;
                        }
                        else{
                            if(n[c][0] != -1)
                                cadena = cadena + " - " + n[c][0]*(-1) + Palabra.UNIDAD_IMAGINARIA;
                            else
                                cadena = cadena + " - " + Palabra.UNIDAD_IMAGINARIA;
                        }
                    }
                    else{
                        if(n[c][0] > 0)
                            cadena = cadena + n[c][0];
                        else
                            cadena = cadena + "- " + n[c][0]*(-1);
                    }
                }
                else{
                    if(c == 1){
                        if(n[c][0] > 0)
                            cadena = cadena + " + " + Fraccion.verFraccion(n[c]) + Palabra.UNIDAD_IMAGINARIA;
                        else{
                            auxiliar[0] = n[c][0]*(-1);
                            auxiliar[1] = n[c][1];
                            cadena = cadena + " - " + Fraccion.verFraccion(auxiliar) + Palabra.UNIDAD_IMAGINARIA;
                        }
                    }
                    else{
                        if(n[c][0] < 0){
                            auxiliar[0] = n[c][0]*(-1);
                            auxiliar[1] = n[c][1];
                            cadena = cadena + "- " + Fraccion.verFraccion(auxiliar);
                        }
                        else
                            cadena = cadena + Fraccion.verFraccion(n[c]);
                    }
                }
            }
        }
        else{
            if(n[0][0] != 0 && n[1][0] == 0){
                if(n[0][1] == 1){
                    if(n[0][0] < 0)
                        cadena = cadena + "- " + n[0][0]*(-1);
                    else
                        cadena = cadena + n[0][0];
                }
                else{
                    if(n[0][0] < 0){
                        auxiliar[0] = n[0][0]*(-1);
                        auxiliar[1] = n[0][1];
                        cadena = cadena + "- " + Fraccion.verFraccion(auxiliar);
                    }
                    else
                        cadena = cadena + Fraccion.verFraccion(n[0]);
                }
            }
            else{
                if(n[0][0] == 0 && n[1][0] != 0){
                    if(n[1][1] == 1){
                        if(n[1][0] != 1 && n[1][0] != -1){
                            if(n[1][0] < 0)
                                cadena = cadena + "- " + n[1][0]*(-1) + Palabra.UNIDAD_IMAGINARIA;
                            else
                                cadena = cadena + n[1][0] + Palabra.UNIDAD_IMAGINARIA;
                        }
                        else{
                            if(n[1][0] == 1)
                                cadena = cadena + Palabra.UNIDAD_IMAGINARIA;
                            else
                                cadena = cadena + "- " + Palabra.UNIDAD_IMAGINARIA;
                        }
                    }
                    else{
                        if(n[1][0] < 0){
                            auxiliar[0] = n[1][0]*(-1);
                            auxiliar[1] = n[1][1];
                            cadena = cadena + "- " + Fraccion.verFraccion(auxiliar) + Palabra.UNIDAD_IMAGINARIA;
                        }
                        else
                            cadena = cadena + Fraccion.verFraccion(n[1]) + Palabra.UNIDAD_IMAGINARIA;
                    }
                }
            }
        }
        
        return cadena;
    }
    
    public static boolean sonDivisibles(Long a, Long b){
        return a%b == 0;
    }
}
