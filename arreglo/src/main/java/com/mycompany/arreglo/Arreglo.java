/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arreglo;

/**
 *
 * @author Carlos I. Padilla Herrera
 */
public class Arreglo {

     public static void main(String[] args) {
          // a. Crea un arreglo llamado numeros
          int[] numeros = {12, 10, 4, 24, 5, 9};

          // b. Realiza un ciclo que recorra todo el arreglo e imprima cada valor
          for (int i = 0; i < numeros.length; i++) {
               System.out.println("El arreglo en la posicion " + i + " tiene el numero " + numeros[i]);
          }
     }
}