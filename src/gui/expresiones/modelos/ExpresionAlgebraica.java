/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.expresiones.modelos;

import gui.cadenas.modelos.Caracter;
import gui.cadenas.modelos.Palabra;
import static gui.interfaces.IPalabra.UNIDAD_IMAGINARIA;

/**
 *
 * @author Benjamin
 */
public class ExpresionAlgebraica {
    private final Palabra expresion;
    private final ListaExpresiones lista;
    
    public ExpresionAlgebraica(Palabra expresion) {
        this.expresion = expresion;
        this.lista = new ListaExpresiones();
    }
    
    /**
     * <pre>
     * Controla si una cadena de texto tiene el formato de una expresión algebráica válida.
     * 
     * 
     * </pre>
     * @return 
     */
    public boolean expresionValida() {
        String caracteresValidos = "0123456789+-*/" + UNIDAD_IMAGINARIA;
        int contadorParentesis = 0;
        
        for(Caracter c : this.expresion) {
            if(c.getCaracter().equals('('))
//                String caracterAnterior = c.getAnterior().getCaracter().toString();
//                if(c.getAnterior().getCaracter() != null && !caracteresValidos.contains(caracterAnterior))
//                    return false;
                contadorParentesis++;
            if(c.getCaracter().equals(')'))
//                String caracterSiguiente = c.getSiguiente().getCaracter().toString();
//                if(c.getSiguiente().getCaracter() != null && !caracteresValidos.contains(caracterSiguiente))
//                    return false;
                contadorParentesis--;
        }
        
        this.desespaciadoParentesis();
        
        Palabra a_evaluar;
        for(Caracter c : this.expresion) {
            if(c.getCaracter().equals('(')) {
                
            }
        }
        
        return contadorParentesis == 0;
    }
    
    /**
     * <pre>
     * Se quita los espacios que hay detrás y delante de cada paréntesis,
     * sean estos paréntesis que se abren o se cierran.
     * </pre>
     */
    private void desespaciadoParentesis() {
        for(Caracter c = this.expresion.getCar(0) ; c.getCaracter() != null ; c = c.getSiguiente()) {
            if(c.equals('(') || c.getCaracter().equals(')')) {
                while(c.getAnterior().equals(' '))
                    this.expresion.borrarCaracter(c.getAnterior().getIndice());
                while(c.getSiguiente().equals(' '))
                    this.expresion.borrarCaracter(c.getSiguiente().getIndice());
            }
        }
    }
}
