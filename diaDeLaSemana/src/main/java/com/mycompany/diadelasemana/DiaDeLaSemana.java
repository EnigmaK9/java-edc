package com.mycompany.diadelasemana;

import java.util.Scanner;

/**
 * Clase principal para determinar el dia de la semana basado en la entrada del usuario.
 * Autor: enigma
 */
public class DiaDeLaSemana {

    public static void main(String[] args) {
        // Crear un objeto Scanner
        Scanner semana = new Scanner(System.in);
        
        // Crear una variable para almacenar el numero del dia
        int dia;
        
        // Solicitar la entrada del usuario
        System.out.println("Introduce un numero del 1 al 7 para conocer el dia de la semana:");
        
        // Leer la entrada del usuario
        dia = semana.nextInt();
        
        // Estructura switch-case para determinar el dia de la semana
        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miercoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sabado");
                break;
            case 7:
                System.out.println("Domingo");
                break;
            default:
                // Manejar entrada no valida
                System.out.println("Numero no valido.");
                break;
        }
        
        // Cerrar el objeto Scanner
        semana.close();
    }
}