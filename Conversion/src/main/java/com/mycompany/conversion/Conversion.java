/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conversion;

/**
 *
 * @author enigmak9
 */
public class Conversion {
    public static void main(String[] args) {
        String input;
        int number;
        System.out.println("Enter a number:");
        input = System.console().readLine();
        number = Integer.parseInt(input);
        System.out.println(number + " pesos");
        System.out.println(number*18.31 + " dollars");
        System.out.println(number*19.61 + " euros");
    }
}
