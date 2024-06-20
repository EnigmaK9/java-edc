/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;

/**
 *
 * @author enigmak9
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] menuRestaurant = new String[5];
        menuRestaurant[0] = "Vegetarian Hot-Dog";
        menuRestaurant[1] = "Potato Salad";
        menuRestaurant[2] = "Corn Bread";
        menuRestaurant[3] = "Roasted Broccoli";
        menuRestaurant[4] = "Coffe Icecream";
        
        menuRestaurant[3] = "Baked cauliflower";
        System.out.println(menuRestaurant[3]);
        System.out.println(menuRestaurant.length);
    }
    
}
