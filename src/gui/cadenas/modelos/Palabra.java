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
    private int cantidad;
    private String palabra;
    private static final Fila FILA = new Fila();

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
                    break;
                    
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
        
        return EXITO_COMPLEJO;
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
                i = arreglo[1].atoi();
                
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