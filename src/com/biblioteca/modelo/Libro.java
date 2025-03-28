package com.biblioteca.modelo;

import com.biblioteca.util.Constantes;

/**
 * Clase que representa un libro en la biblioteca.
 * Hereda de RecursoBiblioteca y añade el atributo editorial.
 */
public class Libro extends RecursoBiblioteca {
    private String editorial;

    public Libro(String nombre, String autor, String editorial, int fechaPublicacion) {
        //se utiliza para llamar al constructor de la clase padre
        super(validarCampo(nombre, "nombre"), validarCampo(autor, "autor"), validarFecha(fechaPublicacion));
        this.editorial = validarCampo(editorial, "editorial");
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override//representacion de caracteres tipo String
    public String toString() {
        return super.toString() + ", Editorial: " + editorial;
    }

    // Métodos de validación
    private static String validarCampo(String campo, String nombreCampo) {
        if (campo == null || campo.trim().isEmpty()) {
            //Manejo de excepsiones
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