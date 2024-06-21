/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arreglobidimensional;

/**
 *
 * @author Carlos I. Padilla Herrera
 */
public class ArregloBidimensional {
    public static void main (String[] args){
        String[][] values;
        values = new String[][]{{"Manzana","Rojo"},{"Platano","Amarillo"},{"Naranja","Naranja"},{"Fresa","Rosa"}};
        
        System.out.println(values[1][0]); // esto imprime Platano
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(values[i][j] + " ");
            } // este for imprime todo el arreglo
    }
    
    }
}
