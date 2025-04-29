package com.biblioteca.servicios;
import com.biblioteca.modelo.Libro;
import com.biblioteca.util.Constantes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Clase que almacena y recupera libros.
 * Ya no maneja la lógica de negocio.
 */
public class Biblioteca implements IBiblioteca {
    private List<Libro> libros;
    private GestorPersistencia gestorPersistencia;

    // Constructor por defecto
    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.gestorPersistencia = new GestorPersistencia(); // Inicialización correcta aquí
    }

    /**
     * Constructor que recibe una lista de libros al crear la Biblioteca.
     */
    public Biblioteca(List<Libro> libros) {
        this.libros = (libros != null) ? new ArrayList<>(libros) : new ArrayList<>();
        this.gestorPersistencia = new GestorPersistencia(); // Inicialización correcta aquí
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }


    @Override
    public Libro buscarLibroPorNombre(String nombre) {
        for (Libro libro : libros) {
            if (libro.getNombre().equalsIgnoreCase(nombre)) {
                return libro;
            }
        }
        return null;//Si el bucle for-each completa su ejecución sin encontrar un libro que coincida, el método devuelve null
    }

    @Override
    public void eliminarLibro(String nombre) {
        Iterator<Libro> iterador = libros.iterator();
        while (iterador.hasNext()) {
            if (iterador.next().getNombre().equalsIgnoreCase(nombre)) {
                iterador.remove();

            }
        }
    }

    @Override
    public void actualizarLibro(Libro libro) {
        boolean actualizado = false;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getNombre().equalsIgnoreCase(libro.getNombre())) {
                libros.set(i, libro);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {//niega la variable actualizado
            throw new IllegalArgumentException(Constantes.ERROR_LIBRO_NO_ENCONTRADO);
        }
    }

    @Override
    public List<Libro> listarLibros() {
        return new ArrayList<>(libros); // Devuelve una copia para evitar modificaciones externas.
    }
    @Override
    public long contarLibrosPorAutor(String autor) {
        return libros.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
                .count();
    }

    @Override
    public List<Libro> filtrarLibrosPorGenero(String genero) {
        return libros.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero)) // Asegúrate de que el libro tenga un método getGenero()
                .sorted(Comparator.comparing(Libro::getNombre)) // Ordenar alfabéticamente
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        return libros.stream()
                .filter(Libro::estaDisponible) // Asegúrate de que el libro tenga un método isDisponible()
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> listarLibrosNoDisponibles() {
        return libros.stream()
                .filter(libro -> !libro.estaDisponible())
                .collect(Collectors.toList());
    }

    @Override
    public void guardarLibrosEnArchivo(String nombreArchivo) {
        gestorPersistencia.guardarLibrosEnArchivo(libros, nombreArchivo);
    }

    @Override
    public void cargarLibrosDesdeArchivo(String nombreArchivo) {
        // Llamar al método del GestorPersistencia para cargar los libros
        gestorPersistencia.cargarLibrosDesdeArchivo(libros, nombreArchivo);
    }
}
