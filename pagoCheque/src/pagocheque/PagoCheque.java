/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pagocheque;

/**
 *
 * @author Enigm
 */
public class PagoCheque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double cantidadPagoCheque = 380 * 6;
        double saldo = 9880;
        double pagoPorDia = 380;
        double diasTrabajados = saldo / pagoPorDia;
        Math.round(diasTrabajados);
        System.out.println("Usted ha trabajado: " + diasTrabajados + " dias ");
        // TODO code application logic here
    }
    
}
