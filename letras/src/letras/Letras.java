
package letras;

public class Letras {
    public static void main(String[] args) {
        char[][] bloqueDeLetras = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'}
        };
        System.out.println(bloqueDeLetras[3]);
        
        for (int indice = 0; indice < bloqueDeLetras.length; indice++) {
            for (int j = 0; j < bloqueDeLetras[indice].length; j++) {
                System.out.print(bloqueDeLetras[indice][j] + " ");
            }
            System.out.println();
        }
    }
}
