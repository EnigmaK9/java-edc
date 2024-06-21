package com.mycompany.linkedlist;

import java.util.LinkedList;

/**
 * Clase para demostrar el uso de LinkedList
 */
public class listaEnlazada {
    public static void main(String[] args) {
        LinkedList<String> animales = new LinkedList<>();
        animales.add("perro");
        animales.add("gato");
        animales.add("oso");

        System.out.println("Lista enlazada: " + animales);
        
        String a = animales.get(1);
        System.out.println("Elemento en el índice 1: " + a);
    }
}