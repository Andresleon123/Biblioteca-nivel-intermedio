package com.biblioteca.servicios;

import com.biblioteca.modelo.Libro;
import com.biblioteca.util.Constantes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GestorBiblioteca {
    private IBiblioteca biblioteca;

    public GestorBiblioteca(IBiblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String agregarLibro(String nombre, String autor, String editorial, int fechaPublicacion, String genero, boolean disponible) {
        try {
            validarDatosLibro(nombre, autor, editorial, fechaPublicacion );
            biblioteca.agregarLibro(new Libro(nombre, autor, editorial, fechaPublicacion, genero, disponible));
            return Constantes.EXITO_AGREGAR_LIBRO;
        } catch (IllegalArgumentException e) {
            return Constantes.ERROR_INESPERADO;
        }
    }

        public List<Libro> buscarLibrosPorNAEF(String desplegableDeBusqueda, String valorDeBusquedaNAEF) {
        try {
            if (esCampoInvalido(valorDeBusquedaNAEF)) {
                throw new IllegalArgumentException(Constantes.ERROR_CAMPOS_VACIOS);
            }
            return biblioteca.listarLibros().stream()
                    .filter(libro -> coincidedesplegableDeBusqueda(libro, desplegableDeBusqueda, valorDeBusquedaNAEF))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(manejarExcepcion(e));
            return Collections.emptyList();
        }
    }

    public String eliminarLibroPorNombre(String nombre) {
        try {
            if (esCampoInvalido(nombre)) {
                return Constantes.ERROR_CAMPOS_VACIOS;
            }

            if (biblioteca.buscarLibroPorNombre(nombre) == null) {
                return Constantes.ERROR_LIBRO_NO_ENCONTRADO;
            }

            biblioteca.eliminarLibro(nombre);
            return Constantes.EXITO_ELIMINAR_LIBRO;
        } catch (Exception e) {
            return manejarExcepcion(e);
        }
    }

    public void actualizarNombreLibro(String nombreActual, String nuevoNombre) {
        Libro libro = obtenerLibroPorNombre(nombreActual);
        libro.setNombre(nuevoNombre);
        biblioteca.actualizarLibro(libro);
    }

    public void actualizarAutorLibro(String nombre, String nuevoAutor) {
        Libro libro = obtenerLibroPorNombre(nombre);
        libro.setAutor(nuevoAutor);
        biblioteca.actualizarLibro(libro);
    }

    public void actualizarEditorialLibro(String nombre, String nuevaEditorial) {
        Libro libro = obtenerLibroPorNombre(nombre);
        libro.setEditorial(nuevaEditorial);
        biblioteca.actualizarLibro(libro);
    }

    public void actualizarFechaLibro(String nombre, int nuevaFecha) {
        Libro libro = obtenerLibroPorNombre(nombre);
        libro.setFechaPublicacion(nuevaFecha);
        biblioteca.actualizarLibro(libro);
    }

    public List<Libro> obtenerListaDeLibros() {
        return biblioteca.listarLibros();
    }

    private boolean esCampoInvalido(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private boolean esfechaValido(int fecha) {
        return fecha <= java.time.Year.now().getValue();
    }

    private void validarDatosLibro(String nombre, String autor, String editorial, int fecha) {
        if (esCampoInvalido(nombre) || esCampoInvalido(autor) || esCampoInvalido(editorial)) {
            throw new IllegalArgumentException(Constantes.ERROR_CAMPOS_VACIOS);
        }
        if (!esfechaValido(fecha)) {
            throw new IllegalArgumentException(Constantes.ERROR_FECHA_INVALIDO);
        }
    }

    private String manejarExcepcion(Exception e) {
        return Constantes.ERROR_INESPERADO;
    }

    private boolean coincidedesplegableDeBusqueda(Libro libro, String desplegableDeBusqueda, String valorDeBusquedaNAEF) {
        switch (desplegableDeBusqueda.toLowerCase()) {
            case "nombre":
                return libro.getNombre().equalsIgnoreCase(valorDeBusquedaNAEF);
            case "autor":
                return libro.getAutor().equalsIgnoreCase(valorDeBusquedaNAEF);
            case "editorial":
                return libro.getEditorial().equalsIgnoreCase(valorDeBusquedaNAEF);
            case "año":
                return String.valueOf(libro.getFechaPublicacion()).equals(valorDeBusquedaNAEF);
            default:
                // Manejo del caso por defecto
                System.out.println("Criterio de búsqueda no válido: " + desplegableDeBusqueda);
                return false;
                // O puedes lanzar una excepción si prefieres
        }
    }

    // Método para obtener el libro por nombre
    private Libro obtenerLibroPorNombre(String nombre) {
        Libro libro = biblioteca.buscarLibroPorNombre(nombre);
        if (libro == null) {
            throw new IllegalArgumentException("Error: El libro '" + nombre + "' no existe.");
        }
        return libro;
    }
}