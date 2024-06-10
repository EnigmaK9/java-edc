/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recibemascota;

import java.util.ArrayList;

/**
 *
 * @author Carlos I. Padilla Herrera
 */
public class RecibeMascota {

    String nombrePerro;
    int edad;
    String raza;
    String tamanio;
    String nombreDuenio;

    public RecibeMascota(String nombrePerro, int edad, String raza, String tamanio, String nombreDuenio) {
        this.nombrePerro = nombrePerro;
        this.edad = edad;
        this.raza = raza;
        this.tamanio = tamanio;
        this.nombreDuenio = nombreDuenio;
    }

    public static void main(String[] args) {
        // a. Crear un arreglo dinámico de tipo de la clase
        ArrayList<RecibeMascota> listaMascotas = new ArrayList<>();

        // b. Hacer instancias para crear objetos de tipo de la clase
        RecibeMascota mascota1 = new RecibeMascota("Firulais", 3, "Labrador", "Grande", "Juan Pérez");
        RecibeMascota mascota2 = new RecibeMascota("Tyzon", 2, "Bulldog", "Mediano", "Ana García");
        RecibeMascota mascota3 = new RecibeMascota("Luna", 1, "Chihuahua", "Pequeño", "Luis Martínez");
        RecibeMascota mascota4 = new RecibeMascota("Rocky", 4, "Pastor Alemán", "Grande", "Marta López");
        RecibeMascota mascota5 = new RecibeMascota("Max", 3, "Golden Retriever", "Grande", "Pedro Hernández");
        RecibeMascota mascota6 = new RecibeMascota("Bella", 5, "Beagle", "Mediano", "Sofía González");
        RecibeMascota mascota7 = new RecibeMascota("Lucky", 2, "Poodle", "Pequeño", "Carlos Ramírez");

        // c. Agregar los objetos creados al arreglo
        listaMascotas.add(mascota1);
        listaMascotas.add(mascota2);
        listaMascotas.add(mascota3);
        listaMascotas.add(mascota4);
        listaMascotas.add(mascota5);
        listaMascotas.add(mascota6);
        listaMascotas.add(mascota7);

        // d. Imprimir la cantidad de perros que se encuentran en la estética
        System.out.println("Perros actuales en la estetica: " + listaMascotas.size());
    }
}
