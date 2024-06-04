/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.apellido;

/**
 *
 * @author enigma
 */
import java.util.Scanner;  

public class Apellido {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);  
        System.out.println("Introduce tu nombre: ");
        String str= sc.nextLine();              //reads string  
        String strNew = str.replaceAll("([a-z])", "");
        System.out.println("La primera letra del nombre introducido es: " + strNew);
    }
}
