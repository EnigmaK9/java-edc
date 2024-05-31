/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.diadelasemana;

import java.util.Scanner;

/**
 * Clase principal para determinar el día de la semana basado en la entrada del usuario.
 * Autor: enigma
 */
public class DiaDeLaSemana {

    public static void main(String[] args) {
        // Crear un objeto Scanner
        Scanner semana = new Scanner(System.in);
        
        // Crear una variable para almacenar el número del día
        int dia;
        
        // Solicitar la entrada del usuario
        System.out.println("Introduce un número del 1 al 7 para conocer el día de la semana:");
        
        // Leer la entrada del usuario
        dia = semana.nextInt();
        
        // Estructura switch-case para determinar el día de la semana
        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sábado");
                break;
            case 7:
                System.out.println("Domingo");
                break;
            default:
                // Manejar entrada no válida
                System.out.println("Número no válido.");
                break;
        }
        
        // Cerrar el objeto Scanner
        semana.close();
    }
}