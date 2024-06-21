/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 * Online Java Compiler
// Use this editor to write, compile and run your Java code online
/**
 *
 * @author Carlos I. Padilla Herrera
 */
package cuentabancariasandy;

public class CuentaBancariaSandy {

    /**
     * @param args the command line arguments
     */

        public static void main(String[] args) throws InterruptedException {
        double saldo = 20000.99;
        double cantidadDepositada = 1000.00;
        saldo = saldo + cantidadDepositada;
        System.out.println("el saldo es: " + saldo);
        //el saldo ahora tiene 21000.99
        Thread.sleep(2000);
        double cantidadARetirar = 1500;
        saldo = saldo - cantidadARetirar;
        System.out.println("el saldo ahora tiene: " + saldo);
        //el saldo ahora tiene 19500.99
    }

}
