/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Benjamin
 */
public class Fraccion {
    private Long[] fraccion = new Long[2];
    
    public Fraccion(Long numerador, Long denominador){
        this.fraccion[0] = numerador;
        this.fraccion[1] = denominador;
    }
    
    public Fraccion(Fraccion fraccion){
        this.fraccion[0] = fraccion.verNumerador();
        this.fraccion[1] = fraccion.verDenominador();
    }

    public Fraccion(){
        this.fraccion[0] = Long.valueOf(0);
        this.fraccion[1] = Long.valueOf(1);
    }
    
    /**
     * @return the fraccion
     */
    public Long[] getNumero() {
        return fraccion;
    }

    /**
     * @param numero the fraccion to set
     */
    public void setNumero(Long[] numero) {
        this.fraccion = numero;
    }
    
    public Long verNumerador() {
        return this.fraccion[0];
    }
    
    public void setNumerador(Long numerador) {
        this.fraccion[0] = numerador;
    }
    
    public Long verDenominador() {
        return this.fraccion[1];
    }
    
    public void setDenominador(Long denominador) {
        this.fraccion[1] = denominador;
    }
    
    /**
     * Suma la fracción <i>n1</i> con la fracción <i>n2</i>
     * @param n Fracción que se le sumará a la fracción que llama al método.
     * @return El resultado de la suma entre <i>n1</i> y <i>n2</i>
     */
    public void sdf(Fraccion n){
        if(this.fraccion == null || n.fraccion == null)
            return;
        
        this.simplificar();
        n.simplificar();
        
	if(!this.verDenominador().equals(n.verDenominador())){
            if(Numero.sonDivisibles(this.verDenominador(), n.verDenominador()) || Numero.sonDivisibles(n.verDenominador(), this.verDenominador())){
                if(Numero.sonDivisibles(this.verDenominador(), n.verDenominador())){
                    this.setNumerador(this.verNumerador() + this.verDenominador() / n.verDenominador() * n.verNumerador());
                    this.setDenominador(this.verDenominador());
                }
                else{
                    this.setNumerador(n.verDenominador() / this.verDenominador() * this.verNumerador() + n.verNumerador());
                    this.setDenominador(n.verDenominador());
                }
            }
            else{
                Long denominador = this.verDenominador() * n.verDenominador();
                this.setNumerador(n.verDenominador() * this.verNumerador() + this.verDenominador() * n.verNumerador());
                this.setDenominador(denominador);
            }
	}
        else{
            this.setNumerador(this.verNumerador() + n.verNumerador());
            this.setDenominador(this.verDenominador());
        }
        
        this.simplificar();
    }
    
    /**
     * Multiplica a la fracción que llama por la fracción <i>n</i>
     * @param n fracción que multiplicará a la fracción de llamada
     */
    public void mdf(Fraccion n){        
        this.simplificar();
        n.simplificar();
        
	this.setNumerador(this.verNumerador() * n.verNumerador());
	this.setDenominador(this.verDenominador() * n.verDenominador());
        
        this.simplificar();
    }
    
    public void ddf(Fraccion n){
        this.simplificar();
        n.simplificar();
        
	this.setNumerador(this.verNumerador() * n.verDenominador());
	this.setDenominador(this.verDenominador() * n.verNumerador());
        
        this.simplificar();
    }
    
    public static String verFraccion(Long[] n){
	if(n[0].equals(n[1]))
            return "1";
	else
	{
            if(n[1]==1) return n[0].toString();
            
            else return n[0].toString() + "/" + n[1].toString();
	}
    }
    
    public void simplificar(){
        if(this.fraccion == null)
            return;
        
        if(this.verDenominador() < 0){
            this.fraccion[0] = this.fraccion[0] * (-1);
            this.fraccion[1] = this.fraccion[1] * (-1);
        }
        
        if(this.verDenominador().equals(this.verNumerador())){
            this.setNumerador(Long.valueOf(1));
            this.setDenominador(Long.valueOf(1));
            return;
        }
        
        Fraccion positivos = new Fraccion();
        if(this.verNumerador() < 0) positivos.setDenominador(this.verNumerador() * (-1));
        else positivos.setNumerador(this.verNumerador());
        if(this.verDenominador() < 0) positivos.setDenominador(this.verDenominador() * (-1));
        else positivos.setDenominador(this.verDenominador());
        
        Long i;
        if(positivos.verNumerador() < positivos.verDenominador()){
            for(i = positivos.verNumerador() ; i!=0 ; i--){
                if(positivos.verNumerador() % i == 0 && positivos.verDenominador() % i == 0)
                    break;
            }
            if(i != 0){
                this.setNumerador(this.verNumerador()/i);
                this.setDenominador(this.verDenominador()/i);
            }
        }
        else{
            for(i = positivos.verDenominador() ; i!=0 ; i--){
                if(positivos.verNumerador() % i == 0 && positivos.verDenominador() % i == 0)
                    break;
            }
            this.setNumerador(this.verNumerador()/i);
                this.setDenominador(this.verDenominador()/i);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Arrays.deepHashCode(this.fraccion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fraccion other = (Fraccion) obj;
        if (!Arrays.deepEquals(this.fraccion, other.fraccion)) {
            return false;
        }
        return true;
    }
    
}
