/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pilas;
import java.util.Stack;

/**
 *
 * @author Enigm
 */
public class Pilas {
    public static void main(String[] args) {
        // a. Importa las clases necesarias para construir una pila.
        Stack<String> camisas = new Stack<>();

        // b. Crea una pila llamada camisas y agrega 3 camisas.
        camisas.push("Camisa blanca");
        camisas.push("Camisa azul");
        camisas.push("Camisa favorita");

        // c. Una vez agregadas, imprime cuál es la última camisa agregada.
        System.out.println("La ultima camisa agregada es: " + camisas.peek());

        // a. Finalmente, crea un bucle condicional donde mientras la pila no esté vacía, imprimas la camisa que se está sacando.
        System.out.println("Sacando camisas de la pila:");
        while (!camisas.isEmpty()) {
            System.out.println(camisas.pop());
        }
    }
}
