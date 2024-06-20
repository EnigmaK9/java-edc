package com.mycompany.leertexto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class escribirTexto {
    public static void main(String[] args) {
        String dato = "Esta es la información que tendrá el archivo de salida";
        String rutaArchivo = "/Users/enigmak9/NetBeansProjects/leerTexto/src/main/java/com/mycompany/leertexto/texto.txt";
        
        try (Writer salida = new FileWriter(rutaArchivo)) {
            salida.write(dato);
            System.out.println("El archivo se ha escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}