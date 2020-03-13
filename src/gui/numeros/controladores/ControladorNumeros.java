/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.controladores;

import gui.cadenas.vistas.VentanaCadenas;
import gui.interfaces.IControladorNumeros;
import gui.numeros.modelos.Numero;
import gui.numeros.vistas.VentanaNumeros;
import java.awt.event.KeyEvent;

/**
 *
 * @author Benjamin
 */
public class ControladorNumeros implements IControladorNumeros {
    private VentanaNumeros ventana;
    
    public ControladorNumeros(VentanaCadenas ventanaPadre, Numero numero){
        this.ventana = new VentanaNumeros(this, ventanaPadre);
        this.ventana.setTitle("Resultado");
        this.ventana.setLocationRelativeTo(null);
        setNumero(numero);
        this.ventana.setVisible(true);
    }

    @Override
    public final void setNumero(Numero numero) {
        
        String cadena = numero.verComplejo();
        
        this.ventana.setEtiquetaNumero(cadena);
    }

    @Override
    public void salir(KeyEvent evt) {
        char c = evt.getKeyChar();            
        if (!Character.isLetter(c)) { //sólo se aceptan letras, Enter, Del, Backspace y espacio
            switch(c) {
                case KeyEvent.VK_ENTER: 
                    this.cancelar();
                    break;
                case KeyEvent.VK_ESCAPE:
                    this.cancelar();
                    break;
            }
        }
    }
    
    public void cancelar(){
        this.ventana.dispose();
    }
}
