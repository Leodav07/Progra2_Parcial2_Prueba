/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author hnleo
 */
public class MyFile {

    private File file = null;

    public void setFile(String dir) {
        file = new File(dir);
    }

    public void info() {
        if (file.exists()) {
            System.out.println("\n SI EXISTE: \n----------");
            System.out.println("Nombre: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Padre Path: " + file.getAbsoluteFile().getParentFile().getParent());
            System.out.println("Bytes: " + file.length());
            if (file.isFile()) {
                System.out.println("ES UN ARCHIVO");

            } else if (file.isDirectory()) {
                System.out.println("ES UN FOLDER");
                System.out.println("Ultima Modificación: " + new Date(file.lastModified()));
            } else {
                System.out.println("Aun no existe.");
            }
        }
    }

    public void crearArchivo() throws IOException {
        if (file.createNewFile()) {
            System.out.println("Se ha creado con éxito.");
        } else {
            System.out.println("No se pudo crear.");
        }

    }

    public void crearFolder() throws IOException {
        if (file.mkdirs()) {
            System.out.println("Se ha creado con éxito.");
        } else {
            System.out.println("No se pudo crear.");
        }
    }

    public boolean borrar(File file) throws IOException {
        if (file.isDirectory()) {

            if (file != null) {
                for (File f : file.listFiles()) {
                    borrar(f);
                }
            }

        }
        return file.delete();

    }

    public File getFile() {
        return file;
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File f : dir.listFiles()) {
                if (!f.isHidden()) {
                    tree(f, tab + "--");
                }
            }
        }

    }

    void tree() {
        tree(file, "-");
    }

    public void MostarDir() {
        double sumaFiles = 0;
        int contF = 0, contD = 0;
        if(file.isDirectory()){
        if (file.exists()) {
            System.out.println("\nDirectorio de: " + file.getAbsolutePath() + "\n");
            System.out.println("Ultima Modificacion        -    Tipo    -  Tamaño   -   Nombre");
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    System.out.println(new Date(f.lastModified()) + "    FILE      " + (f.length()/1024) + " KB          " + f.getName());
                    sumaFiles += f.length();
                    contF++;
                } else if (f.isDirectory()) {
                    System.out.println(new Date(f.lastModified()) + "    <DIR>      " + " -           " + f.getName());
                    contD++;
                }

            }
            
        double espacioLibre = file.getFreeSpace() / (1024*1024*1024);
            System.out.println("\n"+contF + " Archivos " + String.format("%.2f", sumaFiles/1024) + " KB");
            System.out.println(contD + " Directorios " + String.format("%.2f", espacioLibre) + " GB libres");
        } else {
            System.out.println("No hay nada que mostrar.");

        }
    }else{
            System.out.println("Esta opción solamente es habilitada para directorios.");
        }
        
    }
}
