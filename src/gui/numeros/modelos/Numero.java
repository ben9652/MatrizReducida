/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.numeros.modelos;

import java.util.Arrays;

/**
 *
 * @author Benjamin
 */
public class Numero {
    private String cadena;
    int[][] numero = new int[2][2];

    public Numero(String cadena) {
        this.cadena = cadena;
    }

    public String getNumero() {
        return cadena;
    }

    public void setNumero(String cadena) {
        this.cadena = cadena;
    }
    
//    /*Las condiciones que se deben cumplir para que se acepte un número complejo son las siguientes:
//            1) los signos '+' o '-' deberían permitir ingresar otro número.
//            2) Que la i está delante o detrás de un número. Generalizando, podría estar de esta forma (in/d), o de esta otra (n/di), respectivamente.
//    */
//    public boolean esComplejo()
//    {
//        int i,c,bi=0;
//        boolean b;
//        Caracter unidad_imaginaria = new Caracter('i');
//        Caracter slash = new Caracter('/');
//        Caracter signo_mas = new Caracter('+');
//        Caracter signo_menos = new Caracter('-');
//        Palabra expresion = new Palabra(this.cadena);
//        for(i=0,b=true,c=0;i<expresion.length();i++)
//        {
//            if(!isdigit(expresion.getCar(i)))	//Si el elemento [i] de la cadena no es un número, se accionará el if.
//            {
//                if(expresion.getCar(i).equals(unidad_imaginaria))
//                {
//                    bi++;
//                    if(bi>1)
//                            return false;
//                }
//                if(expresion.getCar(i).equals(slash) && b==true)	//Si el elemento es un slash y aún no se detectó tal caracter, se hará entender, con la variable 'c', que se lo acaba de detectar.
//                {
//                    if(expresion.getCar(0).equals(slash))
//                            return false;
//                    b=false;
//                    c++;
//                    if(expresion.getCar(i-1).equals(signo_menos))
//                            return false;
//                }
//                else
//                {
//                    if(i!=0 && isdigit(expresion.getCar(i-1)) || i!=0 && expresion.getCar(i-1).equals(unidad_imaginaria) && bi<=1)
//                    {
//                        if(expresion.getCar(i).equals(signo_menos) || expresion.getCar(i).equals(signo_mas))
//                        {
//                            if(isdigit(expresion.getCar(i+1)) || expresion.getCar(i+1).equals(unidad_imaginaria) && bi<=1)
//                            {
//                                c=0;
//                                continue;
//                            }
//                            else
//                                return false;
//                        }
//                    }
//                    if(expresion.getCar(i).equals(unidad_imaginaria))
//                    {
//                        if(isdigit(expresion.getCar(i-1)) && expresion.getCar(i+1).equals(slash))
//                                return false;
//                    }
//                    if((i!=0 || !expresion.getCar(i-1).equals(slash)) && !expresion.getCar(i).equals(signo_menos) && !expresion.getCar(i).equals(unidad_imaginaria))	//Si el elemento actual no es el primero, o si el elemento anterior no es un slash, y si además, el elemento actual no es un '-', no se cumple el formato.
//                        return false;
//                    if(isdigit(expresion.getCar(i-1)) && expresion.getCar(i).equals(signo_menos))            //Si el elemento anterior es un n�mero y si el actual es un '-', no se cumple el formato.
//                        return false;
//                }
//            }
//            else
//            {
//                if(c==1 && !expresion.getCar(i).equals(new Caracter('0')))
//                    b=true;
//            }
//        }
//        return b;
//    }
//    
//    public int[][] StrToNum(String cadena)
//    {
//        int i=0,j,k = 0,p,c,b=0,br,bi,bslash,naux,haynumeros;
//        char[] cad = cadena.toCharArray();
//        Palabra cadF = new Palabra(cadena);
//        Character[] aux1 = new Character[50];
//        Character[] aux = new Character[50];
//        int[][] n = new int[2][2];
//        n[0][0]=0; n[1][0]=0; n[0][1]=1; n[1][1]=1;
//        do
//        {
//                aux = null;
//                p=0;
//                if((cad[i]=='+' || cad[i]=='-') && b!=0)
//                {
//                        aux[p]=cad[i];
//                        p++;
//                        i++;
//                }
//                /*
//                Cuando se cumplan estas condiciones:
//
//                - El caracter actual es un '+' o '-', y el caracter anterior es un dígito o una 'i', y el siguiente caracter es un dígito o una 'i', o
//                - El caracter anterior es vacío y el actual no es el primer elemento.
//
//                se saldrá del bucle.
//                */
//                boolean primera = cadF.getCar(i).getCaracter() == '+' || cadF.getCar(i).getCaracter() == '-';
//                boolean segunda = isdigit(cadF.getCar(i).getAnterior().getCaracter()) || cadF.getCar(i).getAnterior().getCaracter() == 'i';
//                boolean tercera = isdigit(cadF.getCar(i).getSiguiente().getCaracter()) || cadF.getCar(i).getSiguiente().getCaracter() == 'i';
//                boolean cuarta = cadF.getCar(i).getAnterior() == null && i!=0;
//                for(br=0,bi=0,bslash=0;!(primera && segunda && tercera) && !cuarta;i++,p++,b++){
//                    
//                }
//                
//                for(br=0,bi=0,bslash=0;(cad[i]!='+' && cad[i]!='-' || !isdigit(cad[i-1]) && cad[i-1]!='i' || !isdigit(cad[i+1]) && cad[i+1]!='i') && (cad[i-1]!='\0' || i==0);i++,p++,b++)
//                {
//                        aux[p]=cad[i];
//                        if(cad[i]=='i')
//                                bi++;
//                        if(cad[i]=='/')
//                                bslash++;
//                }
//                if(bi==0)
//                {
//                        br++;
//                        n[0][0]=1;
//                }
//                else
//                        n[1][0]=1;
//                aux1 = null;
//                if(bi!=0)
//                {
//                        for(j=0,c=0,p=0,bslash=0;j<strlen(aux);j++)
//                        {
//                                switch(aux[j])
//                                {
//                                        case '+':break;
//
//                                        case '-':n[1][0]=n[1][0]*(-1);
//                                                        break;
//
//                                        case '/':bslash++;
//                                                        break;
//
//                                        case 'i':break;
//
//                                        default:if(bslash!=0)
//                                                        {
//                                                                if(c==0)
//                                                                {
//                                                                        naux=atoi(Arrays.toString(aux1));
//                                                                        n[1][0]=n[1][0]*naux;
//                                                                        aux1=null;
//                                                                        c++;
//                                                                }
//                                                                aux1[k]=aux[j];
//                                                                k++;
//                                                        }
//                                                        else
//                                                        {
//                                                                aux1[p]=aux[j];
//                                                                p++;
//                                                        }
//                                                        break;
//                                }
//                        }
//                        for(k=0,haynumeros=0;k<strlen(aux1);k++)
//                        {
//                                if(isdigit(aux1[k]))
//                                        haynumeros++;
//                        }
//                        if(haynumeros!=0)
//                                naux=atoi(Arrays.toString(aux1));
//                        else
//                                naux=1;
//                        if(bslash!=0)
//                                n[1][1]=n[1][1]*naux;
//                        else
//                                n[1][0]=n[1][0]*naux;
//                        if(aux1[0]=='\0' && aux[0]=='-')
//                                n[1][0]=-1;
//                        if(aux1[0]=='\0' && aux[0]=='+')
//                                n[1][0]=1;
//                }
//                else
//                {
//                        for(j=0,c=0,p=0,bslash=0;j<strlen(aux);j++)
//                        {
//                                switch(aux[j])
//                                {
//                                        case '+':break;
//
//                                        case '-':n[0][0]=n[0][0]*(-1);
//                                                        break;
//
//                                        case '/':bslash++;
//                                                        break;
//
//                                        default:if(bslash!=0)
//                                                        {
//                                                                if(c==0)
//                                                                {
//                                                                        naux=atoi(Arrays.toString(aux1));
//                                                                        n[0][0]=n[0][0]*naux;
//                                                                        aux1 = null;
//                                                                        k=0;
//                                                                        c++;
//                                                                }
//                                                                aux1[k]=aux[j];
//                                                                k++;
//                                                        }
//                                                        else
//                                                        {
//                                                                aux1[p]=aux[j];
//                                                                p++;
//                                                        }
//                                                        break;
//                                }
//                        }
//                        naux=atoi(Arrays.toString(aux1));
//                        if(bslash!=0)
//                                n[0][1]=n[0][1]*naux;
//                        else
//                                n[0][0]=n[0][0]*naux;
//                }
//        }while(i<cad.length);
//        return n;
//    }
    
    /*
    Primero identificaré las 'i' que haya en la cadena. De ahí, extraeré la parte imaginaria de la cadena
    */
    private void strtonum(){
        final Caracter unidad_imaginaria = new Caracter('i');
        String cadSinEspacios = this.cadena.trim();
        Palabra expresion = new Palabra(cadSinEspacios);
        if(expresion.ocurrencias(unidad_imaginaria) == 1){
            
        }
    }
    
    public static int strlen(Character[] cad){
        int c = 0;
        for(int i = 0;cad[i]!=null;i++)
            c++;
        return c;
    }
    
    public static Integer potenciaNumero(Integer numero, Integer potencia){
        if(potencia == 0)   return 1;
        if(potencia == 1)   return numero;
        else                return numero * potenciaNumero(numero, potencia-1);
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
