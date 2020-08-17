/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.expresiones.modelos;

import gui.cadenas.modelos.Palabra;
import java.util.Iterator;

/**
 *
 * @author Benjamin
 */
public class ListaExpresiones {
    
    private class Expresion {
        private Expresion siguiente;
        private Palabra numero;
        private char nombre;
        
        public Expresion() {
            this.numero = null;
            this.siguiente = null;
        }

        public Expresion(Palabra numero) {
            this.numero = numero;
            this.siguiente = null;
        }

        public Expresion(Palabra numero, char nombre) {
            this(numero);
            this.nombre = nombre;
        }

        public Palabra getNumero() {
            return this.numero;
        }

        public void darNombre(char nombre) {
            this.nombre = nombre;
        }
        
        public char obtenerNombre() {
            return this.nombre;
        }
        
        public Expresion getSiguiente() {
            return this.siguiente;
        }
        
        public void setSiguiente(Expresion siguiente) {
            this.siguiente = siguiente;
        }
    }

    private int longitud;
    private Expresion primera;
    private Expresion ultima;
    
    private String letras = "abcdefghijklmnopqrstuvwxyz";
    
    public ListaExpresiones() {
        this.longitud = 0;
        this.primera = null;
        this.ultima = null;
    }

    public Iterator<Expresion> iterator() {
        return new Iterator<Expresion>() {
            private int i = 0;
            
            @Override
            public boolean hasNext() {
                return i < getLongitud();
            }

            @Override
            public Expresion next() {
                i++;
                return obtenerExpresion(i-1);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }
    
    public String agregarExpresion(Palabra palabra) {
        if(longitud > 26) return "No se puede agregar más elementos.";
        if(palabra.getPalabra().isEmpty()) return "La palabra no tiene caracteres.";
        
        Expresion expresion = new Expresion(palabra, letras.charAt(longitud));
        
        if(this.primera == null) {
            this.primera = expresion;
            this.ultima = expresion;
            this.longitud++;
        }
        else {
            this.ultima.setSiguiente(expresion);
            this.ultima = expresion;
            this.longitud++;
        }
        
        return "Expresión agregada.";
    }
    
    public int getLongitud() {
        return this.longitud;
    }
    
    public Expresion obtenerExpresion(int posicion) {
        Expresion muestra = this.primera;
        if(posicion >= 0 && posicion < this.longitud) {
            if(posicion == this.longitud - 1) return this.ultima;
            for(int c = 0 ; muestra.getSiguiente() != null ; c++, muestra = muestra.getSiguiente()) {
                if(c == posicion)
                    break;
            }
            return muestra;
        }
        else return new Expresion();
    }
}
