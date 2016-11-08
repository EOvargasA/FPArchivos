package fparchivos;

import java.util.Scanner;
//Librarías necesarias para trabajar con archivos.
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
//Libraría para el manejo de excepciones.
import java.io.IOException;
public class FPArchivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String o, d;
        int n, b, e;
        String c [][] = new String [2][2];
        do{
            o = menu();
            //si se manda a llamar el primer comando
            if (o.equals("1")){
                n = pedirint("el numero de alumnos a registrar");
                do{
                    c = llenararreglo(n);
                    b = es (c);
                }while (b == 0);
                do{
                d = pedirstring("el nombre del archivo");
                e = verificararchivo (d);
                }while (e == 1);
                crearArchivo(d,c,n);
            }
        //.equals verifica si la cadena o es lo que esta entre comillas    
        }while (o.equals("2") || o.equals("1")); 
    }
    public static int verificararchivo (String d){
        //Instanciamos un objeto de la clase File 
        //el nombre del archivo sera el mismo que el puesto anteriormente
        File archivo = new File(d+".txt");
        //Verificamos si existe el archivo
        if (archivo.exists()){
            System.out.println("El archivo ya existe");
            return 1;
        }
        return 0;
    }
    public static void crearArchivo(String d, String [][] c,int n){
        //Paso 1 Instanciamos un objeto de la clase File 
        //al instanciar escribimos como parámetro 
        //el nombre del archivo para manipularlo
        File archivo = new File(d+".txt");
        
        //Paso 2. Si no existe el archivo
        if (!archivo.exists())
        {   try {  //try nos sirve para manejar excepciones. En caso de que algo
            //pueda salga mal.
            //Creamos un archivo nuevo. 
            archivo.createNewFile();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }
        //Paso 3. Escritura en el archivo
        try {
            //Instanciamos un objeto de la clase PrintWriter
            //como parámetros enviamos el la instancia de File y el formato de
            //archivo de texto
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
            //Escribimos el contenido del archivo.
            escribir.println("Hola mundo");
            //Cerramos el archivo.
            escribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void leerArchivo(){
        // Paso 1. Instanciamos un objeto de la clase File y una variable cadena
        File archivo = new File ("miArchivo.txt");
        String cadena="";
        try {//Par el manejo de excepciones
            //Instanciamos un objeto de la clase FileReader y otro de la clase
            //BufferedReader, los cuales nos serviran para dar lectura al archivo
            //deben estar dentro de try.
           FileReader lectura = new FileReader(archivo);
           BufferedReader bufferL = new BufferedReader(lectura);
           //Paso 2. Recorremos el archivo.
           while (cadena!=null){ //Mientras la cadena no sea nula
               cadena = bufferL.readLine(); //Leemos líena por línea el archivo.
               if(cadena!=null) { //Si no encontramos null dentro del archivo
                   System.out.println(cadena); //Lo muestra en pantalla.
               }
           }
           //Paso 3. Cerramos las instancias de BufferedReader y FileReader.
           bufferL.close();
           lectura.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    public static String menu (){
        String n;
        Scanner teclado = new Scanner (System.in);
        System.out.println("Bienvenido al registro de alumnos.");
        System.out.println("Por favor introduzca una opción del menú:");
        System.out.println("");
        System.out.println("1.  Registrar Alumnos.");
        System.out.println("2.  Mostrar Registro.");
        System.out.println("3.  Salir.");
        n = teclado.next();
        return n;
    }
    public static int pedirint (String a){
        int n;
        Scanner teclado = new Scanner (System.in);
        System.out.println("Introduce "+a);
        n = teclado.nextInt();
        return n;
    }
    public static String pedirstring (String a){
        String n;
        Scanner teclado = new Scanner (System.in);
        System.out.println("Introduce "+a);
        n = teclado.nextLine();
        return n;
    }
    public static String [][] llenararreglo (int n){
        Scanner teclado = new Scanner (System.in);
        String [][] c = new String [n][2];
        for (int a = 0; a != n; a++){
            System.out.println("Introduce el nombre");
            c [a][0] = teclado.nextLine();
            System.out.println("Introduce la matricula");
            c [a][1] = teclado.nextLine();
        }
        return c;
    }
    public static int es (String [][] a){
        for(int n = 0; n != 2; n++){
            if (unico(a,n) == 0){
                return 0;
            }
        }
        return 1;
    }
    public static int unico (String [][] a, int n){
        for (int b = 0; b != a.length - 1; b++){
            if (original(a,n,b) == 0){
                return 0;
            }
        }
        return 1;
    }
    public static int original (String [][] a, int n, int b){
        for (int c = b + 1; c != a.length - 1; c++){
            if (a[b][n].equals(a[c][b])){
                return 0;
            }
        }
        return 1;
    }
}