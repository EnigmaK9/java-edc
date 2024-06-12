/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colas;


/**
 * Programa para practicar el uso de colas en Java
 * 
 * Autor: Carlos I. Padilla Herrera
 */

import java.util.LinkedList;
import java.util.Queue;
public class Colas {
    public static void main(String[] args) {
        // a. Importa las clases necesarias para construir una cola.
        Queue<String> usuarios = new LinkedList<>();

        // b. Crea una cola llamada usuarios y agrega 5 nombres de tus companieros de clase.
        usuarios.add("Evelyn");
        usuarios.add("Karina");
        usuarios.add("Francisco");
        usuarios.add("Javier");
        usuarios.add("Sandra");

        // c. Una vez agregados, elimina 3 elementos de la cola e imprime el mensaje
        for (int i = 0; i < 3; i++) {
            System.out.println("El nombre a eliminar es: " + usuarios.poll());
        }

        // d. Finalmente, imprime otro mensaje que diga: El proximo nombre a eliminar es: <Nombre a eliminar>.
        System.out.println("El proximo nombre a eliminar es: " + usuarios.peek());
    }
}