/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.miperfil;

/**
 *
 * @author enigma
 */
public class MiPerfil {

    public static void main(String[] args) {
        String nombre = "Carlos I. Padilla Herrera";
        int edad = 30;
        double salarioDeseado = 23000;
        char genero = 'm';
        boolean buscandoTrabajo = true;
        
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Salario Deseado: " + salarioDeseado);
        System.out.println("Genero: " + genero);
        System.out.println("Buscando Trabajo: " + (buscandoTrabajo ? "Sí" : "No"));
    }
}
