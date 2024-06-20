/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.listaenlazada;

import java.util.LinkedList;

/**
 *
 * @author enigmak9
 */
public class ListaEnlazada {

    public static void main(String[] args) {
        LinkedList<String> animales = new LinkedList<String>();
        animales.add("perro");
        animales.add("gato");
        animales.add("vaca");
       animales.add("borrego");
        

        System.out.println("Lista enlazada: " + animales);

        animales.add(0, "caballo");
        System.out.println("Lista enlazada actualizada: " + animales);

        String aux = animales.get(2);
        System.out.println("Elemento del índice 2 es: " + aux);

        String aux2 = animales.remove(2);
        System.out.println("Elemento removido: " + aux2);
        System.out.println("La lista actualizada es: " + animales);
    }
}
