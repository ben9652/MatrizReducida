/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author Benjamin
 */
public interface IControladorCadenas {

    public void btnAceptar(ActionEvent evt);

    public void btnCancelar(ActionEvent evt);

    public void txtExpresionPresionarTecla(KeyEvent evt);
}
