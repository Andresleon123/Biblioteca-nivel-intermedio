package com.biblioteca.modelo;

import com.biblioteca.util.Constantes;

/**
 * Clase que representa un libro en la biblioteca.
 * Hereda de RecursoBiblioteca y añade el atributo editorial.
 */
public class Libro extends RecursoBiblioteca {
    private String editorial;
    private String genero; // Nuevo atributo para el género
    private boolean disponible; // Nuevo atributo para la disponibilidad

    public Libro(String nombre, String autor, String editorial, int fechaPublicacion, String genero, boolean disponible) {
        // Se utiliza para llamar al constructor de la clase padre
        super(validarCampo(nombre, "nombre"), validarCampo(autor, "autor"), validarFecha(fechaPublicacion));
        this.editorial = validarCampo(editorial, "editorial");
        this.genero = validarCampo(genero, "género"); // Inicializar el género
        this.disponible = disponible; // Inicializar la disponibilidad
    }

    public String getEditorial() {
        return editorial;
    }

    public String getGenero() {
        return genero; // Método para obtener el género
    }

    public boolean isDisponible() {
        return disponible; // Método para verificar si el libro está disponible
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setGenero(String genero) {
        this.genero = genero; // Método para establecer el género
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible; // Método para establecer la disponibilidad
    }

    @Override // Representación de caracteres tipo String
    public String toString() {
        return super.toString() + ", Editorial: " + editorial + ", Género: " + genero + ", Disponible: " + disponible;
    }

    // Métodos de validación
    private static String validarCampo(String campo, String nombreCampo) {
        if (campo == null || campo.trim().isEmpty()) {
            // Manejo de excepciones
            throw new IllegalArgumentException("Error: El campo '" + nombreCampo + "' no puede estar vacío o nulo.");
        }
        return campo;
    }

    private static int validarFecha(int fecha) {
        int fechaActual = java.time.Year.now().getValue();
        if (fecha > fechaActual) {
            throw new IllegalArgumentException(Constantes.ERROR_FECHA_INVALIDO);
        }
        return fecha;
    }
}