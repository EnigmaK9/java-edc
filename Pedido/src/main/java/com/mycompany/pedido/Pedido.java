package com.mycompany.pedido;

/**
 *
 * Author: enigma
 */
public class Pedido {

    public static void main(String[] args) {
        double costoArticulo = 30.99;
        boolean listoEnviar = false;

        if (costoArticulo > 24.00) {
            System.out.println("El valor del articulo es alto!");
        }

        if (listoEnviar) {
            System.out.println("Enviado");
        } else {
            System.out.println("Pedido no listo");
        }
    }
}
