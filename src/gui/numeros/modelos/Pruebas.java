/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

/**
 *
 * @author Benjamin
 */
public class Pruebas {
    public static void main(String[] args) {
        Palabra termino = new Palabra("6/78");
        System.out.println(termino.analisisTermino());
//        String cadena = "Hola";
//        char[] c = cadena.toCharArray();
//        Character[] arreglo = toCharacterArray(c);
//        System.out.println(getCharacterString(arreglo));
    }
    
    public static int strlen(Character[] cad){
        int c = 0;
        for(int i = 0;cad[i]!=null;i++)
            c++;
        return c;
    }
    
    public static Character[] toCharacterArray(char c[]){
        Character[] nuevacadena = new Character[c.length];
        for(int i = 0;i<nuevacadena.length;i++)
            nuevacadena[i] = c[i];
        return nuevacadena;
    }
    
    public static String getCharacterString(Character[] cad){
        String cadena = cad[0].toString();
        String aux;
        for(int i = 1;i<cad.length;i++){
            aux = cad[i].toString();
            cadena = cadena.concat(aux);
        }
        return cadena;
    }
}
