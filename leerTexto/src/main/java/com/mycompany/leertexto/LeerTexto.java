package com.mycompany.leertexto;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;

public class LeerTexto {
    public static void main(String[] args) {
        char[] arreglo = new char[33];
        try {
            Reader entrada = new FileReader("/texto.txt");
            entrada.read(arreglo);
            System.out.println("Lo que se leyo en la transmisión: ");
            System.out.println(arreglo);
            entrada.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
