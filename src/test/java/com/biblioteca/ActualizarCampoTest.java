package com.biblioteca;

import com.biblioteca.UI.LogicaBiblioteca;
import com.biblioteca.modelo.Libro;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;
public class ActualizarCampoTest {

    private LogicaBiblioteca logica;

    @BeforeMethod
    public void setUp() {
        logica = new LogicaBiblioteca();
        logica.gestor.agregarLibro("1984", "Orwell", "Planeta", 1949, "Distopía", true);
    }

    @Test
    public void testActualizarCampoAutorCorrectamente() {
        logica.actualizarCampo("1984", "George Orwell", "autor");
        Libro libro = logica.buscarLibroPorCriterio("Nombre", "1984").get(0);
        assertEquals(libro.getAutor(), "George Orwell");
    }

    @Test
    public void testActualizarCampoNombreCorrectamente() {
        logica.actualizarCampo("1984", "1984 - Edición Especial", "nombre");
        List<Libro> resultados = logica.buscarLibroPorCriterio("Nombre", "1984 - Edición Especial");
        assertEquals(resultados.size(), 1);
        assertEquals(resultados.get(0).getAutor(), "Orwell");
    }

    @Test
    public void testActualizarCampoEditorialCorrectamente() {
        logica.actualizarCampo("1984", "Editorial Moderna", "editorial");
        Libro libro = logica.buscarLibroPorCriterio("Nombre", "1984").get(0);
        assertEquals(libro.getEditorial(), "Editorial Moderna");
    }

    @Test
    public void testActualizarCampoInvalido() {
        logica.actualizarCampo("1984", "Nueva Categoría", "genero"); // no existe ese campo
        Libro libro = logica.buscarLibroPorCriterio("Nombre", "1984").get(0);
        assertEquals(libro.getAutor(), "Orwell"); // comprobamos que no se modificó
    }
}
