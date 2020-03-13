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
import java.util.Iterator;
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
    
    private List<Long[]> terminos_reales = new ArrayList<>();
    private List<Long[]> terminos_imaginarios = new ArrayList<>();

    public Numero(Palabra cadena) {
        this.cadena = cadena;
        this.strtonum();
        this.setNumero();
    }

    public Long[][] getNumero() {
        return this.numero;
    }

    private void setNumero() {
        Long[] numeroReal = {Long.valueOf(0), Long.valueOf(1)};
        for(Long[] n : this.terminos_reales)
            numeroReal = sdf(numeroReal, n);
        
        Long[] numeroImaginario = {Long.valueOf(0), Long.valueOf(1)};
        for(Long[] n : this.terminos_imaginarios)
            numeroImaginario = sdf(numeroImaginario, n);
        
        this.numero[0] = numeroReal;
        this.numero[1] = numeroImaginario;
    }
    
    private void strtonum(){
        this.agregadoDeNumeros(this.cadena.verTerminosReales(), false);
        this.agregadoDeNumeros(this.cadena.verTerminosImaginarios(), true);
    }
    
    private void agregadoDeNumeros(List<Palabra> terminos, boolean imaginarios){
        
        for (Palabra p : terminos) {
            
            Long[] candidato = new Long[2];
            
            //Inicialiando los numeradores de las partes, real e imaginaria, con 0
            candidato[0] = Long.valueOf(0);
            candidato[1] = Long.valueOf(1);
            
            if(p.ocurrencias('/') > 0){
                String[] num = p.getPalabra().split("/");
                candidato[0] = Caracter.atoi(num[0]);
                candidato[1] = Caracter.atoi(num[1]);
            }
            else
                candidato[0] = Caracter.atoi(p.getPalabra());
            
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
    
    public static String verFraccion(Long[] n){
	if(n[0].equals(n[1]))
            return "1";
	else
	{
            if(n[1]==1) return n[0].toString();
            
            else return n[0].toString() + "/" + n[1].toString();
	}
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
                            cadena = cadena + " + " + verFraccion(n[c]) + Palabra.UNIDAD_IMAGINARIA;
                        else{
                            auxiliar[0] = n[c][0]*(-1);
                            auxiliar[1] = n[c][1];
                            cadena = cadena + " - " + verFraccion(auxiliar) + Palabra.UNIDAD_IMAGINARIA;
                        }
                    }
                    else{
                        if(n[c][0] < 0){
                            auxiliar[0] = n[c][0]*(-1);
                            auxiliar[1] = n[c][1];
                            cadena = cadena + "- " + verFraccion(auxiliar);
                        }
                        else
                            cadena = cadena + verFraccion(auxiliar);
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
                        cadena = cadena + "- " + verFraccion(auxiliar);
                    }
                    else
                        cadena = cadena + verFraccion(n[0]);
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
                            cadena = cadena + "- " + verFraccion(auxiliar) + Palabra.UNIDAD_IMAGINARIA;
                        }
                        else
                            cadena = cadena + verFraccion(n[1]) + Palabra.UNIDAD_IMAGINARIA;
                    }
                }
            }
        }
        
        return cadena;
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
            if(i != 0){
                n[0] = n[0]/i;
                n[1] = n[1]/i;
            }
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
