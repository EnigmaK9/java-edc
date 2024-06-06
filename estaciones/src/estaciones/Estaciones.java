package estaciones;

import java.util.Scanner;

public class Estaciones {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Proporciona el mes: ");
        int mes = Integer.parseInt(scanner.next());
        String estacion = "Estacion desconocida o no existe.";

        if (mes == 1 || mes == 2 || mes == 12) {
            estacion = "invierno";
        } else if (mes == 3 || mes == 4 || mes == 5) {
            estacion = "primavera";
        } else if (mes == 6 || mes == 7 || mes == 8) {
            estacion = "verano";
        } else if (mes == 9 || mes == 10 || mes == 11) {
            estacion = "otoño";
        }

        System.out.println("La estacion es: " + estacion);
    }
}
