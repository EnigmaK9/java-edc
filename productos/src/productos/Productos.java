/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package productos;

/**
 *
 * @author Carlos I. Padilla Herrera
 */
public class Productos {
    public static void main(String[] args) {
        String productos[] = {"laptop", "CPU", "tablet", "teclado"};
        double precios[] = {2000.00, 500.00, 859.99, 150.00};

        System.out.println("Lista de Productos y Precios:");
        for (int i = 0; i < productos.length; i++) {
            System.out.println(productos[i] + ": $" + precios[i]);
        }
    }
}
