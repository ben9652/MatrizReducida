/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadenas.modelos;

import gui.interfaces.IPalabra;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public final class Palabra implements IPalabra, Iterable<Caracter> {
    private Caracter primero;
    private Caracter ultimo;
    private Integer cantidad = 0;
    private String palabra;
    private static final Fila FILA = new Fila();
    
    private List<Palabra> terminos_reales;
    private List<Palabra> terminos_imaginarios;

    public Palabra(String palabra) {
        if(palabra != null) {
            char[] word = palabra.toCharArray();
            for(char p : word)
                this.insertarCaracterFinal(new Caracter(p));
            this.palabra = palabra;
        }
        else this.palabra = null;
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
    public String toString(){
        return this.getPalabra();
    }
    
    @Override
    public String getPalabra(){
        return palabra;
    }
    
    public List<Palabra> verTerminosReales(){
        return this.terminos_reales;
    }
    
    private void agregarTerminosReales(){
        this.terminos_reales = new ArrayList<>();
        this.terminos_reales = this.recogerTerminos(false);
    }
    
    public List<Palabra> verTerminosImaginarios(){
        return this.terminos_imaginarios;
    }
    
    private void agregarTerminosImaginarios(){
        this.terminos_imaginarios = new ArrayList<>();
        this.terminos_imaginarios = this.recogerTerminos(true);
    }
    
    @Override
    public void setPalabraDesdeCaracter(Caracter car){
        if(car == null) return;
        for( ; car.getCaracter() != null ; car = car.getSiguiente())
            this.insertarCaracterFinal(new Caracter(car.getCaracter()));
    }

    public void mostrarPalabra(){
        Caracter aux=this.primero;
        if(aux!=null){
            for(;aux.getCaracter()!=null;aux=aux.getSiguiente())
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
        if(car.getCaracter() == null) return;
        
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
        car.setIndice(this.cantidad-1);
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
            if(posicion == this.cantidad-1) return this.ultimo;
            for(int c=0;muestra.getSiguiente() != null;c++,muestra = muestra.getSiguiente()){
                if(c==posicion)
                    break;
            }
            return muestra;
        }
        else return new Caracter();
    }
    
    @Override
    public Integer ocurrencias(Character caracter){
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
                    this.cantidad--;
                    for(Caracter c : this)
                        c.setIndice(c.getIndice()-1);
                    break;
                    
                default:
                    if(posicion == this.cantidad-1){
                        this.ultimo = this.ultimo.getAnterior();
                        this.ultimo.setSiguiente(null);
                        this.palabra = this.palabra.substring(0, posicion);
                        this.cantidad--;
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
                        
                        this.cantidad--;
                        
                        for(Caracter car = this.getCar(posicion) ; car.getCaracter() != null ; car = car.getSiguiente())
                            car.setIndice(car.getIndice()-1);
                    }
                    break;
            }
        }
    }
    
    public void reemplazarCaracter(Integer posicion, Character caracter){
        Caracter c = this.primero;
        
        if(posicion<0 || posicion>=this.cantidad) return;
        
        if(posicion == 0){
            String segunda_parte = this.palabra.substring(1);
            this.palabra = caracter.toString() + segunda_parte;
        }
        
        if(posicion == this.cantidad-1){
            String primera_parte = this.palabra.substring(0, posicion);
            this.palabra = primera_parte + caracter.toString();
        }
        
        for(Integer i = 0 ; true ; c = c.getSiguiente(), i++){
            if(Objects.equals(i, posicion)){
                c.setCaracter(caracter);
                String primera_parte = this.palabra.substring(0, posicion);
                String segunda_parte = this.palabra.substring(posicion + 1);
                this.palabra = primera_parte + caracter.toString() + segunda_parte;
                break;
            }
        }
    }
    
    public void borrarOcurrencias(Character ocurrencia){
        for(Caracter c : this){
            if(c.equals(ocurrencia))
                this.borrarCaracter(c.getIndice());
        }
    }
    
    @Override
    public Integer length(){
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
     * <pre>
     * Controla si una cadena de texto tiene el formato de un número real o complejo.
     * 
     * La cadena tendrá un formato válido si se cumplen todas las siguientes condiciones:
     *  1) La cadena no debe estar vacía
     *  2) No debe haber más de 1 signo '+' o '-' seguido.
     *  3) Luego de un signo '+' o '-' debe haber otro número.
     * 
     *  Nota para la siguiente condición: 
     *      Este método elimina los espacios que haya entre términos y signos para facilitar el control.
     * 
     *  4) No se puede escribir un término con espaciados en el medio.
     *  5) Un término es válido solo si cumple las siguientes condiciones:
     *      I) No puede haber más de uno de los siguientes caracteres: '+', '-', '/', y 'i'.
     *      II) No puede haber un slash sin números a la derecha o a la izquierda.
     *      III) No puede haber un signo detrás de un slash.
     *      IV) No se permiten caracteres que no sean los numéricos o los caracteres '+', '-', '/', y 'i'.
     *      V) La unidad imaginaria solo puede estar posicionada detrás, o delante, de un término. No puede estar entre números.
     * 
     * También, al final, ya validado todo, se guardan los términos reales y los imaginarios de este objeto Palabra.
     * </pre>
     * @return Un mensaje que indica el resultado del control.
     */
    public String esComplejo(){
        if(this.getPalabra().isEmpty()) return ERROR_SIN_EXPRESION;
        
        Palabra desespaciada = this.desespaciado_numerosComplejos();
        
        int cuenta_signos = 0;
        for(Caracter c : desespaciada){
            if(mas_o_menos(c)) cuenta_signos++;
            else cuenta_signos = 0;
            
            if(cuenta_signos>1) return ERROR_SIGNOS_SEGUIDOS;
        }
        
        if(mas_o_menos(desespaciada.ultimo)) return ERROR_FALTA_OPERANDO;
        
        //Si hay un espacio en la cadena, se devuelve falso.
        //Esto es para facilitar el formateo de la cadena (más información en el JavaDoc de desespaciado_numerosComplejos()).
        for(Caracter c : desespaciada){
            if(c.equals(' ')) return ERROR_ESPACIOS;
        }
        
        Palabra[] terminos = desespaciada.separarPorSignos();
        
        for(Palabra p : terminos){
            String estado = p.analisisTermino();
            if(!estado.equals(EXITO_TERMINO)) return estado;
        }
        
        this.palabra = desespaciada.palabra;
        
        this.agregarTerminosReales();
        this.agregarTerminosImaginarios();
        
        return EXITO_COMPLEJO;
    }
    
    private Palabra[] separarPorSignos(){
        List<Palabra> listaTerminos = new ArrayList<>();
        Palabra terminoCandidato = new Palabra();
        for(Caracter c : this){
            if(mas_o_menos(c)){
                if(!c.equals(this.primero)){
                    if(!c.getAnterior().equals('/')){
                        listaTerminos.add(new Palabra(terminoCandidato.getPalabra()));
                        terminoCandidato.vaciar();
                        continue;
                    }
                }
                else continue;
            }
            terminoCandidato.insertarCaracterFinal(c);
        }
        listaTerminos.add(new Palabra(terminoCandidato.getPalabra()));
        Palabra[] terminos = new Palabra[listaTerminos.size()];
        int i = 0;
        for(Palabra p : listaTerminos){
            terminos[i] = p;
            i++;
        }
        return terminos;
    }
    
    private List<Palabra> recogerTerminos(boolean imaginarios){
        List<Palabra> terminos = new ArrayList<>();
        
        Palabra auxiliar = new Palabra(this.palabra);
        
        if(auxiliar.primero == null) return terminos;
        
        if(imaginarios){
            auxiliar.eliminarTerminos(false);
            if(auxiliar.primero == null) return terminos;
            for(Caracter c = auxiliar.primero ; c.getCaracter() != null ; c = c.getSiguiente()){
                if(c.equals(UNIDAD_IMAGINARIA)){
                    if(!imaginariaSola(c))
                        auxiliar.borrarCaracter(c.getIndice());
                    else
                        auxiliar.reemplazarCaracter(c.getIndice(), '1');
                }
            }
        }
        else
            auxiliar.eliminarTerminos(true);
        if(auxiliar.primero.getCaracter() == null) return terminos;
        if(auxiliar.primero.equals('+'))
            auxiliar.borrarCaracter(0);
        Palabra termino = new Palabra();
        for(Caracter c : auxiliar){
            boolean esUnSigno = mas_o_menos(c);
            boolean elAnteriorNoEsUnSlash = !c.getAnterior().equals('/');
            boolean terminosVacia = terminos.isEmpty();
            if(esUnSigno && elAnteriorNoEsUnSlash){
                if(i_o_numero(c.getSiguiente())){
                    if(terminosVacia && !(c.getIndice().equals(0) && mas_o_menos(c))){
                        terminos.add(new Palabra(termino.getPalabra()));
                        termino.vaciar();
                    }
                    if(c.equals('-'))
                        termino.insertarCaracterFinal(c);
                }
                else{
                    terminos.add(new Palabra(termino.getPalabra()));
                    termino.vaciar();
                }
            }
            else{
                if(c.getSiguiente().getCaracter() == null){
                    termino.insertarCaracterFinal(c);
                    terminos.add(new Palabra(termino.getPalabra()));
                    break;
                }
                else
                    termino.insertarCaracterFinal(c);
            }
        }
        
        return terminos;
    }
    
    /**
     * Se eliminan términos reales, o imaginarios.
     * 
     * @param imaginarios Se indica qué hacer. Con <i><b>true</b></i> se ordena eliminar los términos imaginarios; con <i><b>false</b></i>, los reales.</pre>
     */
    private void eliminarTerminos(boolean imaginarios){
        if(imaginarios == false){
            Palabra con_imaginarios = this.recogerImaginarios();
            this.vaciar();
            if(con_imaginarios.primero == null)
                this.palabra = "";
            else
                this.setPalabraDesdeCaracter(con_imaginarios.primero);
        }
        else{
            for(Caracter c = this.primero ; c.getCaracter() != null ; c = c.getSiguiente()){
                if(c.equals(UNIDAD_IMAGINARIA)){
                    Caracter auxiliar;
                    //Si la unidad imaginaria está al final del término
                    if(c.getAnterior().isdigit() && (c.getSiguiente().getCaracter() == null || mas_o_menos(c.getSiguiente()))){
                        for(auxiliar = c ; !(mas_o_menos(auxiliar) && !auxiliar.getAnterior().equals('/')) && auxiliar.getCaracter() != null ; auxiliar = auxiliar.getAnterior())
                            this.borrarCaracter(auxiliar.getIndice());
                        if(mas_o_menos(auxiliar))
                            this.borrarCaracter(auxiliar.getIndice());
                    }
                    //Si la unidad imaginaria está al comienzo del término
                    if(c.getSiguiente().isdigit() && (c.getAnterior().getCaracter() == null || mas_o_menos(c.getAnterior()))){
                        if(mas_o_menos(c.getAnterior()))
                            this.borrarCaracter(c.getIndice()-1);
                        for(auxiliar = c ; !(mas_o_menos(auxiliar) && !auxiliar.getAnterior().equals('/')) && auxiliar.getCaracter() != null ; ){
                            this.borrarCaracter(auxiliar.getIndice());
                            auxiliar = auxiliar.getSiguiente();
                        }
                    }
                    if(imaginariaSola(c)){
                        if(mas_o_menos(c.getAnterior())) auxiliar = c.getAnterior();
                        else auxiliar = c;
                        this.borrarCaracter(auxiliar.getIndice());
                    }
                }
            }
        }
    }
    
    private Palabra recogerImaginarios(){
        Palabra nueva = new Palabra();
        for(Caracter c : this){
            if(c.equals(UNIDAD_IMAGINARIA)){
                Caracter auxiliar;
                //Si la unidad imaginaria está al final del término
                if(c.getAnterior().isdigit() && (c.getSiguiente().getCaracter() == null || mas_o_menos(c.getSiguiente()))){
                    for(auxiliar = c ; !(mas_o_menos(auxiliar) && !auxiliar.getAnterior().equals('/')) && auxiliar.getCaracter() != null ; auxiliar = auxiliar.getAnterior()){
                        if(auxiliar.getAnterior().getCaracter() == null) break;
                    }
                    nueva.insertarCaracterFinal(new Caracter(auxiliar.getCaracter()));
                    auxiliar = auxiliar.getSiguiente();
                    for( ; !(mas_o_menos(auxiliar) && !auxiliar.getAnterior().equals('/')) && auxiliar.getCaracter() != null ; auxiliar = auxiliar.getSiguiente())
                        nueva.insertarCaracterFinal(new Caracter(auxiliar.getCaracter()));
                }
                //Si la unidad imaginaria está al comienzo del término
                if(c.getSiguiente().isdigit() && (c.getAnterior().getCaracter() == null || mas_o_menos(c.getAnterior()))){
                    if(mas_o_menos(c.getAnterior()))
                        nueva.insertarCaracterFinal(new Caracter(c.getAnterior().getCaracter()));
                    for(auxiliar = c ; !(mas_o_menos(auxiliar) && !auxiliar.getAnterior().equals('/')) && auxiliar.getCaracter() != null ; auxiliar = auxiliar.getSiguiente())
                        nueva.insertarCaracterFinal(new Caracter(auxiliar.getCaracter()));
                }
                //Si la unidad imaginaria está sola
                if(imaginariaSola(c)){
                    if(c.getAnterior().equals('-'))
                        nueva.insertarCaracterFinal(new Caracter(c.getAnterior().getCaracter()));
                    nueva.insertarCaracterFinal(new Caracter(UNIDAD_IMAGINARIA));
                }
            }
        }
        return nueva;
    }
    
    private static boolean imaginariaSola(Caracter unidad_imaginaria){
        return
                unidad_imaginaria.getAnterior().getCaracter() == null && mas_o_menos(unidad_imaginaria.getSiguiente())
             || mas_o_menos(unidad_imaginaria.getAnterior()) && mas_o_menos(unidad_imaginaria.getSiguiente())
             || mas_o_menos(unidad_imaginaria.getAnterior()) && unidad_imaginaria.getSiguiente().getCaracter() == null
             || unidad_imaginaria.getAnterior().getCaracter() == null && unidad_imaginaria.getSiguiente().getCaracter() == null;
    }
    
    /**
     * <pre>Este método elimina los espacios que hay entre signos '+' y '-', y números o 'ies'.
     * 
     * Por ejemplo:
     *              Palabra palabra = new Palabra("+ 6 /  - 9i  -    7");
     * 
     *              palabra.desespaciado_numerosComplejos() returns "+6 / - 9i-7"
     *</pre>
     * @return Palabra desespaciada entre números o ies, y '+' y '-'.
     */
    private Palabra desespaciado_numerosComplejos() {
        Palabra palabraDesespaciada = new Palabra(this.palabra);
        int wordLength = palabraDesespaciada.length();
        Caracter indice = palabraDesespaciada.primero;
        Caracter[] arreglo;
        
        //Eliminación de los espacios antes y después de la cadena
        for(int i = 0 ; indice.equals(' ') ; indice = indice.getSiguiente()){
            palabraDesespaciada.borrarCaracter(i);
            wordLength--;
        }
        indice = palabraDesespaciada.ultimo;
        for(int i = palabraDesespaciada.length()-1 ; indice.equals(' ') ; i--, indice = indice.getAnterior()){
            palabraDesespaciada.borrarCaracter(i);
            wordLength--;
        }
        
        indice = palabraDesespaciada.primero;
        
        for(int i = 0 ; i<wordLength ; i++, indice = indice.getSiguiente()) {
            if(indice.equals(' ')){
                arreglo = analisisEntreEspacios(indice, i);
                
                indice = arreglo[0];
                
                Palabra numero_indice = new Palabra();
                numero_indice.setPalabraDesdeCaracter(arreglo[1]);
                i = Caracter.atoi(numero_indice.getPalabra()).intValue();
                
                for( ; indice.equals(' ') ; indice = indice.getSiguiente()){
                    palabraDesespaciada.borrarCaracter(i);
                    wordLength--;
                }
            }
            
            if(!FILA.esFilaVacia() && !indice.equals(' ')) FILA.enfila(new Caracter(indice.getCaracter()));
            
            if(indice.equals('/')) FILA.enfila(new Caracter(indice.getCaracter()));
            
            if(indice.isdigit()) FILA.vaciar();
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
                for(Caracter provisorio = indice ; indice.equals(' ') ; indice = indice.getSiguiente(), i++){
                    if(i_o_numero(indice.getSiguiente())){
                        if(!FILA.frente().equals('/') || !mas_o_menos(FILA.frente().getSiguiente())){
                            indice = provisorio;
                            i = p;
                            break;
                        }
                    }
                }
            }
            if(i_o_numero_en_anterior){
                for(Caracter provisorio = indice ; indice.equals(' ') ; indice = indice.getSiguiente(), i++){
                    if(mas_o_menos(indice.getSiguiente())){
                        indice = provisorio;
                        i = p;
                        break;
                    }
                }
            }
        }
        else
            for( ; indice.equals(' ') ; indice = indice.getSiguiente(), i++);
        
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
    
    /**
     * <pre>Valida un término individual. Este puede ser real o complejo.
     * 
     * La técnica es la siguiente:
     * 
     * 1) Recorrer toda la cadena y contar la cantidad de '/', 'i', '+', y '-' que hay.
     * 2) En el caso de haber más de 1 de cada uno de los dos caracteres, se retorna falso.
     * 3) Si hay un '/', se procede a analizar, por detrás, y por delante del slash qué hay:
     *      i) por detrás: solo debe haber números (o una 'i') y una 'i' opcional al final del recorrido hacia atrás.
     *      ii) por delante: puede haber un signo '+' o '-' exactamente delante de '/', pero no en otro lugar
     * y se cumple lo mismo que en i), excepto que la 'i' solo puede estar al final.
     * 4) Si no hay un '/', se verifica que solo sean números, y, en caso de haber una i, que solo esté en el principio, o en el final de la cadena.</pre>
     * 
     * @return 
     */
    private String analisisTermino(){
        Caracter indice_slash = new Caracter(null);
        int cantidad_numeros = 0;
        int numero_slashes = 0;
        int numero_ies = 0;
        int cantidad_signo_mas = 0;
        int cantidad_signo_menos = 0;
        
        for(Caracter c : this){
            if(c.equals('/')){
                numero_slashes++;
                indice_slash = c;
            }
            if(c.equals(UNIDAD_IMAGINARIA))
                numero_ies++;
            if(c.equals('+'))
                cantidad_signo_mas++;
            if(c.equals('-'))
                cantidad_signo_menos++;
        }
        
        String estado = control_cantidad_de_caracteres(numero_slashes, numero_ies, cantidad_signo_mas, cantidad_signo_menos);
        if(!estado.equals(EXITO_CARACTERES_DE_MAS)) return estado;
        
        if(numero_slashes == 1){
            
            //Se analiza detrás del slash
            for(Caracter puntero_paTras = indice_slash ; puntero_paTras.getCaracter() != null ; puntero_paTras = puntero_paTras.getAnterior()){
                if(!puntero_paTras.isdigit()){
                    
                    //Se filtra el caracter '/', y se hace un control de paso
                    if(puntero_paTras.equals('/')){
                        if(puntero_paTras.getSiguiente().getCaracter() == null) return ERROR_MITAD_DERECHA_SIN_NUMERO;
                        continue;
                    }
                    
                    if(mas_o_menos(puntero_paTras)) return ERROR_SIGNO_MAL_PUESTO;
                    
                    //Si el caracter que no es un dígito, NO es una 'i', se retornará falso.
                    if(!puntero_paTras.equals(UNIDAD_IMAGINARIA)) return ERROR_CARACTER_INVALIDO;
                    
                    //Si detrás de la 'i' hay algún caracter, o si adelante de la 'i' no hay un número, se retornará falso.
                    if(puntero_paTras.getAnterior().getCaracter() != null || !puntero_paTras.getSiguiente().isdigit()) return ERROR_MALA_POSICION_DE_I;
                }
            }
            
            //Se analiza adelante del slash
            for(Caracter puntero_paLante = indice_slash ; puntero_paLante.getCaracter() != null ; puntero_paLante = puntero_paLante.getSiguiente()){
                if(!puntero_paLante.isdigit()){
                    
                    //Se filtra el caracter '/', y se hace un control de paso
                    if(puntero_paLante.equals('/')){
                        if(puntero_paLante.getAnterior().getCaracter() == null) return ERROR_MITAD_IZQUIERDA_SIN_NUMERO;
                        continue;
                    }
                    
                    //Si me topo con un signo '+' o '-', estrictamente debe haber un '/' atrás, y un número delante.
                    if(mas_o_menos(puntero_paLante)){
                        if(puntero_paLante.getAnterior().equals('/') && puntero_paLante.getSiguiente().isdigit()) continue;
                        else return ERROR_SIGNO_MAL_PUESTO;
                    }
                    
                    //Si el caracter que no es un dígito, NO es una 'i', se retornará falso.
                    if(!puntero_paLante.equals(UNIDAD_IMAGINARIA)) return ERROR_CARACTER_INVALIDO;
                    
                    //Si delante de la 'i' hay algún caracter, o si detrás de la 'i' no hay un número, se retornará falso.
                    if(puntero_paLante.getSiguiente().getCaracter() != null || !puntero_paLante.getAnterior().isdigit()) return ERROR_MALA_POSICION_DE_I;
                }
                else
                    cantidad_numeros++;
            }
            if(cantidad_numeros == 1 && indice_slash.getSiguiente().equals('0')) return ERROR_DENOMINADOR_CERO;
        }
        else{
            for(Caracter c : this){
                if(!c.isdigit()){
                    if(!c.equals(UNIDAD_IMAGINARIA)) return ERROR_CARACTER_INVALIDO;
                    if(!c.equals(this.primero) && !c.equals(this.ultimo)) return ERROR_MALA_POSICION_DE_I;
                }
            }
        }
        return EXITO_TERMINO;
    }
    
    private String control_cantidad_de_caracteres(int slashes, int ies, int mases, int menoses){
        final String frase_inicial_singular = "No puede haber más de ";
        final String frase_inicial_plural = "No pueden haber: más de ";
        final String frase_final = " en un término.";
        final String y = "y ";
        final String coma = ", ";
        
        final String slash = "un '/'";
        final String i = "una '" + UNIDAD_IMAGINARIA + "'";
        final String mas = "un '+'";
        final String menos = "un '-'";
        
        if(slashes>1 || ies>1 || mases>1 || menoses>1){
            if(!(slashes>1) && !(ies>1) && !(mases>1) && (menoses>1))   return frase_inicial_singular + menos + frase_final;
            if(!(slashes>1) && !(ies>1) && (mases>1) && !(menoses>1))   return frase_inicial_singular + mas + frase_final;
            if(!(slashes>1) && !(ies>1) && (mases>1) && (menoses>1))    return frase_inicial_plural + mas + coma + y + frase_final;
            if(!(slashes>1) && (ies>1) && !(mases>1) && !(menoses>1))   return frase_inicial_singular + i + frase_final;
            if(!(slashes>1) && (ies>1) && !(mases>1) && (menoses>1))    return frase_inicial_plural + menos + coma + y + i + frase_final;
            if(!(slashes>1) && (ies>1) && (mases>1) && !(menoses>1))    return frase_inicial_plural + mas + coma + y + i + frase_final;
            if(!(slashes>1) && (ies>1) && (mases>1) && (menoses>1))     return frase_inicial_plural + menos + coma + mas + coma + y + i + frase_final;
            if((slashes>1) && !(ies>1) && !(mases>1) && !(menoses>1))   return frase_inicial_singular + slash + frase_final;
            if((slashes>1) && !(ies>1) && !(mases>1) && (menoses>1))    return frase_inicial_plural + menos + coma + y + slash + frase_final;
            if((slashes>1) && !(ies>1) && (mases>1) && !(menoses>1))    return frase_inicial_plural + mas + coma + y + slash + frase_final;
            if((slashes>1) && !(ies>1) && (mases>1) && (menoses>1))     return frase_inicial_plural + menos + coma + mas + coma + y + slash + frase_final;
            if((slashes>1) && (ies>1) && !(mases>1) && !(menoses>1))    return frase_inicial_plural + slash + coma + y + i + frase_final;
            if((slashes>1) && (ies>1) && !(mases>1) && (menoses>1))     return frase_inicial_plural + menos + coma + i + coma + y + slash + frase_final;
            if((slashes>1) && (ies>1) && (mases>1) && !(menoses>1))     return frase_inicial_plural + mas + coma + slash + coma + y + i + frase_final;
            if((slashes>1) && (ies>1) && (mases>1) && (menoses>1))      return frase_inicial_plural + menos + coma + mas + coma + slash + coma + y + i + frase_final;
        }
        
        return EXITO_CARACTERES_DE_MAS;
    }
    
    private static boolean mas_o_menos(Caracter car){
        return car.equals('+') || car.equals('-');
    }
    
    private static boolean i_o_numero(Caracter car){
        return car.equals(UNIDAD_IMAGINARIA) || car.isdigit();
    }
}