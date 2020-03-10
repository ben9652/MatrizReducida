/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadenas.modelos;

/**
 *
 * @author Benjamin
 */
public class Fila {
    private Caracter primero;
    private Caracter finalfila;
    private int cantidad;
    private Caracter indefinido = new Caracter();
    
    public Fila(){
        this.primero = null;
        this.finalfila = null;
        this.cantidad = 0;
    }
    
    public boolean esFilaVacia(){
        return this.primero == null;
    }
    
    public void enfila(Caracter n){
        if(this.esFilaVacia()){
            this.primero = n;
            this.finalfila = n;
        }
        this.finalfila.setSiguiente(n);
        this.finalfila = n;
        this.cantidad++;
    }
    
    public void defila(){
        this.primero = this.primero.getSiguiente();
        this.cantidad--;
    }
    
    public Caracter frente(){
        if(this.esFilaVacia())
            return this.indefinido;
        else
            return this.primero;
    }
    
    public void vaciar(){
        this.primero = null;
        this.cantidad = 0;
    }
}
