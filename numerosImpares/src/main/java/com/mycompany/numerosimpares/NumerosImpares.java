/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.numerosimpares;

/**
 *
 * @author Carlos Ignacio Padilla Herrera
 */
public class NumerosImpares {

    public static void main(String[] args) {
        // Inicializar el primer número impar
        int numeroImpar = 1;
        
        // Iterar 15 veces para imprimir los primeros 15 números impares
        for (int i = 0; i < 15; i++) {
            // Imprimir el número impar actual
            System.out.print(numeroImpar + " ");
            
            // Incrementar en 2 para obtener el siguiente número impar
            numeroImpar += 2;
        }
    }
}
