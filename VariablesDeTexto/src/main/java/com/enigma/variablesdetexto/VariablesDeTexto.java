/**
 * Programa para practicar el uso de variables de texto en Java
 * 
 * Autor: Carlos I. Padilla Herrera
 */
import java.util.Scanner; 
//importacion de la clase Scanner

public class VariablesDeTexto {

    public static void main(String[] args) {
        // a. Importa la clase Scanner para la lectura de datos.
        Scanner datos = new Scanner(System.in);

        // c. Crea las variables nombre y edad.
        String nombre;
        int edad;

        // d. Imprime el mensaje y almacena el valor en la variable nombre.
        System.out.print("Ingresa tu nombre completo: ");
        nombre = datos.nextLine();

        // e. Imprime el mensaje y almacena el valor en la variable edad.
        System.out.print("Ingresa tu edad: ");
        edad = datos.nextInt();

        // f. Crear una condicional para verificar si el usuario es mayor o menor de edad.
        if (edad >= 18) {
            System.out.println("Tu nombre es " + nombre + ", tienes " + edad + " anios y eres mayor de edad."); 
        } else {
            System.out.println("Tu nombre es " + nombre + ", tienes " + edad + " anios y eres menor de edad."); 
        }

        // g. Imprimir el mensaje con el numero de caracteres del nombre.
        System.out.println("Tu nombre tiene " + nombre.length() + " caracteres.");

        datos.close(); // Cerrar el objeto Scanner para evitar posibles fugas de recursos
    }
}