package com.biblioteca.servicios;

import com.biblioteca.modelo.Libro;
import java.io.*;
import java.util.List;

public class GestorPersistencia {

    public void guardarLibrosEnArchivo(List<Libro> libros, String nombreArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Libro libro : libros) {
                escritor.write(libro.getNombre() + "," + libro.getAutor() + "," + libro.getEditorial() + "," +
                        libro.getFechaPublicacion() + "," + libro.getGenero() + "," + libro.isDisponible());
                escritor.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los libros: " + e.getMessage());
        }
    }

    public void cargarLibrosDesdeArchivo(List<Libro> libros, String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datosLibro = linea.split(",");
                if (datosLibro.length == 6) {
                    String nombre = datosLibro[0];
                    String autor = datosLibro[1];
                    String editorial = datosLibro[2];
                    int fechaPublicacion = Integer.parseInt(datosLibro[3]);
                    String genero = datosLibro[4];
                    boolean disponible = Boolean.parseBoolean(datosLibro[5]);
                    libros.add(new Libro(nombre, autor, editorial, fechaPublicacion, genero, disponible));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los libros: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en los datos del libro: " + e.getMessage());
        }
    }
}