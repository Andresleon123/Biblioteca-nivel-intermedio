package com.biblioteca.servicios;

import com.biblioteca.modelo.Libro;

import java.util.List;

/**
 * Interfaz que define los métodos que cualquier biblioteca debe implementar.
 */
public interface IBiblioteca {// Se encarga de almacenar
    //Contiene los metodos de los botones
    //Define las operaciones básicas de una biblioteca
    void agregarLibro(Libro libro);

    Libro buscarLibroPorNombre(String nombre);

    void eliminarLibro(String nombre);

    void actualizarLibro(Libro libro);

    List<Libro> listarLibros();

    long contarLibrosPorAutor(String autor);//Contara cuantos libros hay de un autor en espesifico

    List<Libro> filtrarLibrosPorGenero(String genero);//devolverá una lista de libros que coincidan con el género especificado

    List<Libro> listarLibrosDisponibles();

    List<Libro> listarLibrosNoDisponibles();
}
