/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.numeros.modelos.Caracter;
import gui.numeros.modelos.Palabra;

/**
 *
 * @author Benjamin
 */
public interface IPalabra {
    
    String EXITO_CARACTERES_DE_MAS = "Caracteres justos.";
    
    String ERROR_SLASH = "No puede haber más de un '/' en un término.";
    String ERROR_IES = "No puede haber más de una 'i' en un término.";
    String ERROR_MAS = "No puede haber más de un '+' en un término.";
    String ERROR_MENOS = "No puede haber más de un '-' en un término.";
    String ERROR_SLASH_IES = "No pueden haber más de un slash y una 'i' en un término.";
    String ERROR_SLASH_MAS = "No pueden haber más de un slash y un '+' en un término.";
    String ERROR_SLASH_MENOS = "No pueden haber más de un slash y un '-' en un término.";

    public String getPalabra();

    public void setPalabraDesdeCaracter(Caracter car);

    public void vaciar();

    public void trim();

    public void insertarCaracterComienzo(Caracter car);

    public void insertarCaracterFinal(Caracter car);

    public Caracter extraer(int posicion);

    public Caracter getCar(int posicion);

    public Integer ocurrencias(Caracter caracter);

    public void borrarCaracter(Integer posicion);

    public int length();

    public void esPalindromo(Palabra palabra);

    public void invertir(Palabra palabra);

    public Palabra concat(Palabra a);
}
