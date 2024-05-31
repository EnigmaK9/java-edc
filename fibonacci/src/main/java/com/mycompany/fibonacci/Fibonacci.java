/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fibonacci;

/**
 *
 * @author enigma
 */
public class Fibonacci {

    public static void main(String[] args) {
        int contador = 15;
        int numero1 = 0;
        int numero2 = 1;
        
        for (int indice = 0; indice < contador; indice++) {
            System.out.print(numero1 + " ");
            
            int sumaDeLosDosAnteriores = numero1 + numero2;
            numero1 = numero2;
            numero2 = sumaDeLosDosAnteriores;
    }
        System.out.println("Serie de Fibonacci");
    }
}
