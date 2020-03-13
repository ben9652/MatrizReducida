/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.cadenas.modelos.Caracter;
import gui.cadenas.modelos.Palabra;

/**
 *
 * @author Benjamin
 */
public interface IPalabra {
    Character UNIDAD_IMAGINARIA = 'j';
    
    String EXITO_COMPLEJO = "Es un número válido.";
    String ERROR_SIN_EXPRESION = "No hay una expresión que analizar.";
    String ERROR_ESPACIOS = "No pueden existir espacios en un término. Solo entre términos.";
    String ERROR_SIGNOS_SEGUIDOS = "No puede haber más de un signo algebráico seguido.";
    String ERROR_FALTA_OPERANDO = "Falta un operando luego del signo del final.";
    
    String EXITO_TERMINO = "Termino correcto.";
    String EXITO_CARACTERES_DE_MAS = "Caracteres justos.";
    String ERROR_MITAD_DERECHA_SIN_NUMERO = "En un nuevo término, no puede NO haber números delante de un '/'.";
    String ERROR_MITAD_IZQUIERDA_SIN_NUMERO = "En un nuevo término, no puede NO haber números detrás de un '/'.";
    String ERROR_CARACTER_INVALIDO = "No se permiten caracteres fuera de dígitos numéricos, y de +, -, " + UNIDAD_IMAGINARIA + ", y /.";
    String ERROR_MALA_POSICION_DE_I = "La '" + UNIDAD_IMAGINARIA + "' no puede posicionarse en medio de números.";
    String ERROR_DENOMINADOR_CERO = "Un denominador no puede ser 0.";
    String ERROR_SIGNO_MAL_PUESTO = "En medio de un término, solo puede haber un signo estrictamente delante de un slash.";

    public String getPalabra();

    public void setPalabraDesdeCaracter(Caracter car);

    public void vaciar();

    public void trim();

    public void insertarCaracterComienzo(Caracter car);

    public void insertarCaracterFinal(Caracter car);

    public Caracter extraer(int posicion);

    public Caracter getCar(int posicion);

    public Integer ocurrencias(Character caracter);

    public void borrarCaracter(Integer posicion);

    public Integer length();

    public void esPalindromo(Palabra palabra);

    public void invertir(Palabra palabra);

    public Palabra concat(Palabra a);
}
