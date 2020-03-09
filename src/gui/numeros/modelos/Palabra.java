/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import gui.interfaces.IPalabra;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public final class Palabra implements IPalabra, Iterable<Caracter> {
    private Caracter primero;
    private Caracter ultimo;
    private int cantidad;
    private String palabra;
    private static final Fila FILA = new Fila();

    public Palabra(String palabra) {
        char[] word = palabra.toCharArray();
        for(char p : word)
            this.insertarCaracterFinal(new Caracter(p));
        this.palabra = palabra;
    }
    
    public Palabra(){
        this.palabra = null;
    }
    
    @Override
    public Iterator<Caracter> iterator() {
        return new Iterator<Caracter>() {
            private int i = 0;
            
            @Override
            public boolean hasNext() {
                return i < length();
            }

            @Override
            public Caracter next() {
                i++;
                return getCar(i-1);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }
    
    @Override
    public String getPalabra(){
        return palabra;
    }
    
    @Override
    public void setPalabraDesdeCaracter(Caracter car){
        for( ; car.getCaracter() != null ; car = car.getSiguiente())
            this.insertarCaracterFinal(new Caracter(car.getCaracter()));
    }

    public void mostrarPalabra(){
        Caracter aux=this.primero;
        if(aux!=null){
            for(;aux!=null;aux=aux.getSiguiente())
                System.out.print(aux.getCaracter());
        }
        System.out.println("");
    }
    
    @Override
    public void vaciar(){
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
        this.palabra = "";
    }
    
    @Override
    public void trim(){
        int i = 0;
        for(Caracter aux = this.primero ; aux != null ; i++, aux = aux.getSiguiente()){
            if(aux.equals(new Caracter(' ')))
                this.borrarCaracter(i);
        }
    }
    
    @Override
    public void insertarCaracterComienzo(Caracter car){
        car.setSiguiente(this.primero);
        this.primero=car;
    }
    
    @Override
    public void insertarCaracterFinal(Caracter car){
        if(this.primero == null){
            this.primero = car;
            this.ultimo = car;
            this.palabra = car.getCaracter().toString();
            this.cantidad++;
        }
        else{
            car.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(car);
            this.ultimo = car;
            this.palabra += car.getCaracter().toString();
            this.cantidad++;
        }
    }
    
    @Override
    public Caracter extraer(int posicion){
        Caracter caracter = this.getCar(posicion);
        if(caracter.getCaracter() == null) this.borrarCaracter(posicion);
        return caracter;
    }
    
    @Override
    public Caracter getCar(int posicion){
        Caracter muestra = this.primero;
        if(posicion>=0 && posicion<this.cantidad){
            for(int c=0;muestra != null;c++,muestra = muestra.getSiguiente()){
                if(c==posicion)
                    break;
            }
            return muestra;
        }
        else return new Caracter();
    }
    
    @Override
    public Integer ocurrencias(Caracter caracter){
        Integer ocurrencias = 0;
        Caracter actual = this.primero;
        for(int i = 0 ; i<this.length() ; i++, actual = actual.getSiguiente())
            if(actual.equals(caracter)) ocurrencias++;
        return ocurrencias;
    }
    
    @Override
    public void borrarCaracter(Integer posicion){
        
        Caracter aux = this.primero;
        Caracter anterior;
        Caracter siguiente;
        
        if(posicion<0) return;
        
        if(posicion<this.cantidad){
            switch(posicion){
                case 0:
                    this.primero = this.primero.getSiguiente();
                    this.palabra = this.palabra.substring(1);
                    return;
                    
                default:
                    if(posicion == this.cantidad-1){
                        this.ultimo = this.ultimo.getAnterior();
                        this.ultimo.setSiguiente(null);
                        this.palabra = this.palabra.substring(0, posicion);
                    }
                    else{
                        for(int contador=0 ; contador != posicion ; contador++, aux = aux.getSiguiente());

                        anterior = aux.getAnterior();
                        siguiente = aux.getSiguiente();

                        anterior.setSiguiente(siguiente);
                        siguiente.setAnterior(anterior);
                        
                        String subCadenaInicial = this.palabra.substring(0, posicion);
                        String subCadenaFinal = this.palabra.substring(posicion + 1, this.palabra.length());
                        
                        this.palabra = subCadenaInicial + subCadenaFinal;
                    }
                    break;
            }
            this.cantidad--;
        }
    }
    
    @Override
    public int length(){
        return cantidad;
    }
    
    @Override
    public void esPalindromo(Palabra palabra){
        Palabra poriginal = new Palabra(palabra.getPalabra().toLowerCase());
        Palabra paux = new Palabra("a");
        paux.primero=null;
        for(Caracter aux=poriginal.primero;aux!=null;aux=aux.getSiguiente())
            paux.insertarCaracterComienzo(aux);
        for(Caracter aux1=poriginal.primero,aux2=paux.primero;aux1!=null;aux1=aux1.getSiguiente(),aux2=aux2.getSiguiente()){
            if(!Objects.equals(aux1.getCaracter(), aux2.getCaracter())){
                System.out.println("No es palíndromo");
                return;
            }
        }
        System.out.println("Es palíndromo");
    }
    
    @Override
    public void invertir(Palabra palabra){
        Palabra paux = new Palabra("a");
        paux.primero = null;
        for(Caracter aux=palabra.primero;aux!=null;aux=aux.getSiguiente())
            paux.insertarCaracterComienzo(aux);
        palabra.primero = paux.primero;
        for(Caracter aux1=paux.primero,aux2=palabra.primero;aux1!=null;aux1=aux1.getSiguiente(),aux2=aux2.getSiguiente())
            aux2=aux1;
    }
    
    @Override
    public Palabra concat(Palabra a) {
        Palabra nueva = new Palabra(this.getPalabra());
        Caracter aux = a.primero;
        for(int i = 0; i < a.length() ; i++, aux = aux.getSiguiente()) {
            nueva.insertarCaracterFinal(aux);
        }
        return nueva;
    }
    
    /**
     * Este método elimina los espacios que hay entre signos '+' y '-', y números o 'ies'.
     * 
     * Por ejemplo:
     *              Palabra palabra = new Palabra("+ 6 /  - 9i  -    7");
     * 
     *              palabra.desespaciado_numerosComplejos() returns "+6 / - 9i-7"
     *
     * @return
     */
    @Override
    public Palabra desespaciado_numerosComplejos() {
        Palabra palabraDesespaciada = new Palabra(this.palabra);
        int wordLength = palabraDesespaciada.length();
        Caracter indice = palabraDesespaciada.primero;
        Caracter[] arreglo;
        
        for(int i = 0 ; i<wordLength ; i++, indice = indice.getSiguiente()) {
            if(indice.equals(new Caracter(' '))){
                arreglo = analisisEntreEspacios(indice, i);
                
                indice = arreglo[0];
                i = arreglo[1].atoi();
                
                for( ; indice.equals(new Caracter(' ')) ; indice = indice.getSiguiente()){
                    palabraDesespaciada.borrarCaracter(i);
                    wordLength--;
                }
            }
            
            if(!FILA.esFilaVacia() && !indice.equals(new Caracter(' '))) FILA.enfila(new Caracter(indice.getCaracter()));
            
            if(indice.equals(new Caracter('/'))) FILA.enfila(new Caracter(indice.getCaracter()));
        }
        return palabraDesespaciada;
    }
    
    /**
     * <p>Analiza si en los extremos de entre los espacios (o el espacio) están los caracteres
     * requeridos para borrar estos espacios (o el espacio).</p>
     * <p>Si resulta que se encuentran estos caracteres, se devuelve el índice desde el comienzo
     * de los espacio (o espacio), así, en el método que llama a este, se borran todos estos
     * espacios.</p>
     * <p>Si se encuentran caracteres distintos a los requeridos, se devuelve el índice en la
     * posición del primer caracter luego del espacio.</p>
     * <pre>
     * Ejemplo 1:
     *                      indice                         indice
     *                         ∨                             ∨
     *          "+ 6 /  - 9i  -    7" returns "+ 6 /  - 9i  -    7"
     * 
     * Ejemplo 2:
     *              indice                          indice
     *                ∨                               ∨
     *          "+ 6 /  - 9i  -    7" returns "+ 6 /  - 9i  -    7"</pre>
     * 
     * @param indice Señala al caracter desde el que comenzará a analizar.
     * @return El caracter en el que se quedará parado finalmente.
     */
    private static Caracter[] analisisEntreEspacios(Caracter indice, Integer i){
        Caracter[] arreglo = new Caracter[2];
        int p = i;
        
        boolean masOMenos_en_anterior = mas_o_menos(indice.getAnterior());
        boolean i_o_numero_en_anterior = i_o_numero(indice.getAnterior());
        
        if(masOMenos_en_anterior || i_o_numero_en_anterior){
            
            if(masOMenos_en_anterior){
                for(Caracter provisorio = indice ; indice.equals(new Caracter(' ')) ; indice = indice.getSiguiente(), i++){
                    if(i_o_numero(indice.getSiguiente())){
                        if(!FILA.frente().equals(new Caracter('/')) || !mas_o_menos(FILA.frente().getSiguiente())){
                            indice = provisorio;
                            i = p;
                            break;
                        }
                    }
                }
                if(!indice.equals(new Caracter(' ')))
                    FILA.vaciar();
            }
            if(i_o_numero_en_anterior){
                for(Caracter provisorio = indice ; indice.equals(new Caracter(' ')) ; indice = indice.getSiguiente(), i++){
                    if(mas_o_menos(indice.getSiguiente())){
                        indice = provisorio;
                        i = p;
                        break;
                    }
                }
            }
        }
        else
            for( ; indice.equals(new Caracter(' ')) ; indice = indice.getSiguiente(), i++);
        
        arreglo[0] = indice;
        String numero_indice = Integer.toString(i);
        arreglo[1] = new Caracter(numero_indice.charAt(0));
        Caracter aux = arreglo[1];
        for(int contador = 1 ; contador<numero_indice.length() ; aux = aux.getSiguiente(), contador++){
            Caracter nuevo_digito = new Caracter(numero_indice.charAt(contador));
            aux.setSiguiente(nuevo_digito);
        }
        
        return arreglo;
    }
    
    private static boolean mas_o_menos(Caracter car){
        return car.equals(new Caracter('+')) || car.equals(new Caracter('-'));
    }
    
    private static boolean i_o_numero(Caracter car){
        return car.equals(new Caracter('i')) || car.isdigit();
    }
    
//    public boolean esComplejo(){
//        
//    }
    
    /**
     * Analiza si un término está escrito en el formato de número complejo.
     * @return 
     */
    public boolean analisisTermino(){
        Caracter indice_slash = new Caracter(null);
        int numero_slashes = 0;
        int numero_ies = 0;
        
        for(Caracter c : this){
            if(c.equals('/')){
                numero_slashes++;
                indice_slash = c;
            }
            if(c.equals('i'))
                numero_ies++;
        }
        
        if(numero_slashes>1 || numero_ies>1) return false;
        
        if(numero_slashes == 1){
            
            //Se analiza detrás del slash
            for(Caracter puntero_paTras = indice_slash ; puntero_paTras.getCaracter() != null ; puntero_paTras = puntero_paTras.getAnterior()){
                if(!puntero_paTras.isdigit()){
                    //Se filtra el caracter '/', y se hace un control de paso
                    if(puntero_paTras.equals('/')){
                        if(puntero_paTras.getSiguiente().getCaracter() == null) return false;
                        continue;
                    }
                    
                    //Si el caracter que no es un dígito, NO es una 'i', se retornará falso.
                    if(!puntero_paTras.equals('i')) return false;
                    
                    //Si detrás de la 'i' hay algún caracter, o si adelante de la 'i' no hay un número, se retornará falso.
                    if(puntero_paTras.getAnterior().getCaracter() != null || !puntero_paTras.getSiguiente().isdigit()) return false;
                }
            }
            
            //Se analiza adelante del slash
            for(Caracter puntero_paLante = indice_slash ; puntero_paLante.getCaracter() != null ; puntero_paLante = puntero_paLante.getSiguiente()){
                if(!puntero_paLante.isdigit()){
                    //Se filtra el caracter '/', y se hace un control de paso
                    if(puntero_paLante.equals('/')){
                        if(puntero_paLante.getAnterior().getCaracter() == null) return false;
                        continue;
                    }
                    
                    //Si el caracter que no es un dígito, NO es una 'i', se retornará falso.
                    if(!puntero_paLante.equals('i')) return false;
                    
                    //Si delante de la 'i' hay algún caracter, o si detrás de la 'i' no hay un número, se retornará falso.
                    if(puntero_paLante.getSiguiente().getCaracter() != null || !puntero_paLante.getAnterior().isdigit()) return false;
                }
            }
        }
        else{
            for(Caracter c : this){
                if(!c.isdigit()){
                    if(!c.equals('i')) return false;
                    if(!c.equals(this.primero) && !c.equals(this.ultimo)) return false;
                }
            }
        }
        return true;
    }
}