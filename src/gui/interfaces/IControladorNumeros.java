/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.numeros.modelos.Numero;
import java.awt.event.KeyEvent;

/**
 *
 * @author Benjamin
 */
public interface IControladorNumeros {

    public void setNumero(Numero numero);

    public void salir(KeyEvent evt);
    
    public void cancelar();
}
