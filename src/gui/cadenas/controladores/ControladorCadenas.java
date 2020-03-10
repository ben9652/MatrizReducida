/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadenas.controladores;

import gui.cadenas.modelos.Palabra;
import gui.cadenas.vistas.VentanaCadenas;
import gui.interfaces.IControladorCadenas;
import static gui.interfaces.IPalabra.EXITO_COMPLEJO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
        this.probarCadena();
    }
    
    private void probarCadena(){
        Palabra expresion = new Palabra(this.ventana.getText());
        
        if(expresion.getPalabra().isEmpty()) return;
        
        String resultado = expresion.esComplejo();
        
        if(!resultado.equals(EXITO_COMPLEJO))
            JOptionPane.showMessageDialog(null, "Número: " + expresion + "\n" + resultado, "Error", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Número: " + expresion + "\n" + resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(VentanaCadenas.class.getResource("check.png")));
    }

    @Override
    public void btnCancelar(ActionEvent evt) {
        this.cancelar();
    }
    
    private void cancelar(){
        this.ventana.dispose();
    }

    @Override
    public void txtExpresionPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();            
        if (!Character.isLetter(c)) { //sólo se aceptan letras, Enter, Del, Backspace y espacio
            switch(c) {
                case KeyEvent.VK_ENTER: 
                    this.probarCadena();
                    break;
                case KeyEvent.VK_ESCAPE:
                    this.cancelar();
                    break;
//                default:
//                    evt.consume(); //consume el evento para que no sea procesado por la fuente
            }
        }
    }
}
