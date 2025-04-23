package com.biblioteca;

import com.biblioteca.UI.LogicaBiblioteca;
import com.biblioteca.modelo.Libro;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

import static org.testng.Assert.*;

public class FiltrarLibrosPorGeneroTest {

    private LogicaBiblioteca logica;
    private JTextArea textArea;

    @BeforeMethod
    public void setUp() {
        logica = new LogicaBiblioteca();
        textArea = new JTextArea();

        // Agrega libros de varios géneros
        logica.gestor.agregarLibro("El Hobbit", "Tolkien", "Minotauro", 1937, "Fantasía", true);
        logica.gestor.agregarLibro("Cien Años de Soledad", "García Márquez", "Sudamericana", 1967, "Realismo Mágico", true);
    }

    @Test
    public void testFiltrarLibrosGeneroExistente() {
        List<Libro> libros = logica.gestor.filtrarLibrosPorGenero("Fantasía");

        // Simula lo que haría el método sobre el textArea
        textArea.setText("Libros del género Fantasía:\n");
        if (libros.isEmpty()) {
            textArea.append("No se encontraron libros de este género.\n");
        } else {
            for (Libro libro : libros) {
                textArea.append(libro.toString() + "\n");
            }
        }

        String resultado = textArea.getText();
        assertTrue(resultado.contains("El Hobbit"));
        assertTrue(resultado.contains("Libros del género Fantasía"));
    }

    @Test
    public void testFiltrarLibrosGeneroInexistente() {
        List<Libro> libros = logica.gestor.filtrarLibrosPorGenero("Terror");

        textArea.setText("Libros del género Terror:\n");
        if (libros.isEmpty()) {
            textArea.append("No se encontraron libros de este género.\n");
        } else {
            for (Libro libro : libros) {
                textArea.append(libro.toString() + "\n");
            }
        }

        String resultado = textArea.getText();
        assertTrue(resultado.contains("No se encontraron libros de este género"));
    }
}
