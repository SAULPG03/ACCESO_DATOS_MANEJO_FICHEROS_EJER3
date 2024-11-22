package es.ieslosmontecillos;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static void newDirectory(File ruta){
        ruta.mkdir();
    }
    public static void newFile(File ruta) throws IOException {
        ruta.createNewFile();
    }
    public static void listDirectoyandFile(File ruta){
        if(ruta.exists()){
            if(ruta.isDirectory()){
                String[] files = ruta.list();
                for(int i = 0; i < files.length; i++){
                    System.out.println(files[i]);
                }
            }
            else if(ruta.isFile()){
                System.out.println(ruta);
            }
        }
    }

}