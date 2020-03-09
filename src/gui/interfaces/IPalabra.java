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

    public Palabra desespaciado_numerosComplejos();
}
