/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadenas.controladores;

import gui.cadenas.modelos.Palabra;
import gui.cadenas.vistas.VentanaCadenas;
import gui.interfaces.IControladorCadenas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

/**
 *
 * @author Benjamin
 */
public class ControladorCadenas implements IControladorCadenas {
    private VentanaCadenas ventana;
    
    public ControladorCadenas(JDialog ventanaPadre){
        this.ventana = new VentanaCadenas(this, ventanaPadre);
        this.ventana.setTitle("Verificador de números complejos");
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    @Override
    public void btnAceptar(ActionEvent evt) {
        Palabra expresion = new Palabra(this.ventana.getText());
        
    }

    @Override
    public void btnCancelar(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void txtExpresionPresionarTecla(KeyEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
