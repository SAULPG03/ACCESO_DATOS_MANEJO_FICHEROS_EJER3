package es.ieslosmontecillos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        int opcion = 0;
        File directorioPadre = new File("C:\\Users\\usuario\\IdeaProjects\\ACCESO_DATOS_MANEJO_FICHEROS_EJER3\\src\\main\\resources");
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("-----------MENU-----------");
            System.out.println("1. Crear Directorio");
            System.out.println("2. Crear Archivo");
            System.out.println("3. Listar Directorio o Archivo");
            System.out.println("4. Cambiar nombre a Directorio o Fichero");
            System.out.println("5. Borrar Fichero o Directorio");
            System.out.println("6. Insertar cadena de texto en fichero");
            System.out.println("7. Añadir cadena de texto en fichero");
            System.out.println("8. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();//Clean buffer keyboard
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el nombre del directorio");
                    String nombre = sc.nextLine();
                    newDirectory(directorioPadre,nombre);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del archivo");
                    String nombreArchivo = sc.nextLine();
                    try {
                        newFile(directorioPadre,nombreArchivo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Ingrese la ruta del fichero o directorio a listar: ");
                    String ruta = sc.nextLine();
                    listDirectoyandFile(ruta);
                    break;
                case 4:
                    System.out.println("Ingrese la ruta del nombre del fichero o archivo que quieres renombrar: ");
                    String fileRename = sc.nextLine();
                    renameFileorDirectory(fileRename,sc);
                    break;
                case 5:
                    System.out.println("Ingrese la ruta del nombre del fichero o archivo que quieres eliminar: ");
                    String fileDelete = sc.nextLine();
                    deleteFileorDirectory(directorioPadre,fileDelete);
                    break;
                case 6:
                    System.out.println("Ingrese el nombre del fichero a escribir: ");
                    String fileEscribir = sc.nextLine();
                    try {
                        insertStringFile(directorioPadre,fileEscribir,sc);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Te has salido del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while (opcion!=8);
        sc.close();
    }
    public static void newDirectory(File ruta,String nombre){
        String rutaPadre=ruta.getAbsolutePath();
        File newDirectory=new File(rutaPadre,nombre);
        boolean success=newDirectory.mkdir();
        if(success){
            System.out.println("El directorio "+nombre+" ha sido creado con exito");
        }else{
            System.out.println("El directorio "+nombre+" no se puede ser creado");
        }

    }
    public static void newFile(File ruta,String nombre) throws IOException {
        String rutaPadre=ruta.getAbsolutePath();
        File newFile=new File(rutaPadre,nombre);
        boolean success=newFile.createNewFile();
        if(success){
            System.out.println("El fichero "+nombre+" ha sido creado con exito");
        }else{
            System.out.println("El fichero "+nombre+" no se puede ser creado");
        }
    }
    public static void listDirectoyandFile(String path){
        File ruta=new File(path);
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
        }else{
            System.err.println("No se encuentra el fichero o directorio");
        }
    }

    public static void renameFileorDirectory(String name,Scanner sc){
        File ruta=new File(name);
        String newName;
        if(ruta.exists()){
            if(ruta.isDirectory()){
                System.out.println("Introduce el nuevo nombre: ");
                newName = sc.nextLine();

                if(ruta.renameTo(new File(ruta.getAbsolutePath().replace(ruta.getName(),newName)))){
                    System.out.println("Se ha cambiado el nombre al Direcorio");
                }else{
                    System.out.println("No se ha cambiado el nombre al Direcorio");
                }
            } else if (ruta.isFile()) {
                System.out.println("Introduce el nuevo nombre: ");
                newName = sc.nextLine();
                if(ruta.renameTo(new File(ruta.getAbsolutePath().replace(ruta.getName(),newName)))){
                    System.out.println("Se ha cambiado el nombre al Fichero");
                }else{
                    System.out.println("No se ha cambiado el nombre al Fichero");
                }
            }
        }else{
            System.err.println("No se encuentra el fichero o directorio");
        }
    }

    public static void deleteFileorDirectory(File ruta,String fileDelete){
        String rutaPadre= ruta.getAbsolutePath();
        File children=new File(rutaPadre,fileDelete);
        if(children.exists()){
            if(children.delete()){
                System.out.println("Se ha borrado el fichero correctamente");
            }else{
                System.out.println("No se ha borrado el fichero correctamente");
            }
        }else{
            System.err.println("No se encuentra el fichero o directorio");
        }
    }

    public static void insertStringFile(File ruta,String fichero,Scanner sc) throws IOException {
        String rutaPadre= ruta.getAbsolutePath();
        File children=new File(rutaPadre,fichero);
        if (children.exists()) {
            if (children.isFile()) {
                System.out.println("Introduce el texto: ");
                String texto = sc.nextLine();
                FileWriter fw = new FileWriter(children);
                fw.write(texto);
                fw.close();
            } else if (children.isDirectory()) {
                System.err.println("No se escribir en un directorio");
            }
        }else{
            System.err.println("No se encuentra el fichero o directorio");
        }
    }

    public static void appendStringFile(File ruta,String string) throws IOException {
        if (ruta.exists()) {
            if (ruta.isFile()) {
                FileWriter fw = new FileWriter(ruta, true);
                fw.write(string);
                fw.close();
            } else if (ruta.isDirectory()) {
                System.err.println("No se escribir en un directorio");
            }
        }else{
            System.err.println("No se encuentra el fichero o directorio");
        }
    }

}