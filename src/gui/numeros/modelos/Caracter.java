/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Caracter {
    private Character caracter;
    private Caracter siguiente;
    private Caracter anterior;

    public Caracter(Character caracter) {
        this.caracter = caracter;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public Caracter() {
        this.caracter = null;
    }
    
    /**
     * @return the siguiente
     */
    public Caracter getSiguiente() {
        if(this.siguiente != null) return this.siguiente;
        else return new Caracter();
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Caracter siguiente) {
        this.siguiente = siguiente;
    }

    public Caracter getAnterior() {
        if(this.anterior != null) return this.anterior;
        else return new Caracter();
    }

    public void setAnterior(Caracter anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the caracter
     */
    public Character getCaracter() {
        if(this != null) return caracter;
        else return '\u0000';
    }

    /**
     * @param caracter the caracter to set
     */
    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }
    
    public boolean isdigit(){
        if(this.getCaracter() == null) return false;
        String cadenas = "1234567890";
        String aux = this.toString();
        return cadenas.contains(aux);
    }
    
    public static Integer atoi(String c){
        int i = 0;
        int numero = 0;
        Palabra expresion = new Palabra(c);
        Caracter actual;
        boolean esNegativo = false;
        if(expresion.getCar(0).equals(new Caracter('-'))){
            i = 1;
            esNegativo = true;
        }
        for(Integer potencia = expresion.length() ; i<expresion.length() ; i++, potencia--) {
            actual = expresion.getCar(i);
            if(actual.isdigit()){
                if(!esNegativo) numero = numero + charToNum(actual)*Numero.potenciaNumero(10, potencia-1);
                else numero = numero + charToNum(actual)*Numero.potenciaNumero(10, potencia-2);
            }
            else return 0;
        }
        if(!esNegativo) return numero;
        else return numero*(-1);
    }
    
    public Integer atoi(){
        Palabra numero = new Palabra();
        numero.setPalabraDesdeCaracter(this);
        return atoi(numero.getPalabra());
    }
    
    public static Integer charToNum(Caracter c){
        switch(c.getCaracter()){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            default: return null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.caracter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == null) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            if(obj.getClass().getSimpleName().equals("Character")){
                final Character other = (Character) obj;
                return Objects.equals(this.caracter, other);
            }
            else return false;
        }
        else{
            final Caracter other = (Caracter) obj;
            return Objects.equals(this.caracter, other.caracter);
        }
    }
    
    @Override
    public String toString(){
        return this.caracter.toString();
    }
}
