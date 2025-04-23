package com.biblioteca;

import com.biblioteca.UI.LogicaBiblioteca;
import com.biblioteca.modelo.Libro;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BuscarLibroTest {

    private LogicaBiblioteca logica;

    @BeforeMethod
    public void setUp() {
        logica = new LogicaBiblioteca();
        // Agregar libros de prueba
        logica.gestor.agregarLibro("El Quijote", "Cervantes", "Alfaguara", 1605, "Novela", true);
        logica.gestor.agregarLibro("1984", "Orwell", "Planeta", 1949, "Distopía", true);
    }

    @Test
    public void testBuscarLibroPorNombreExistente() {
        List<Libro> resultados = logica.buscarLibroPorCriterio("Nombre", "1984");
        assertEquals(resultados.size(), 1);
        assertEquals(resultados.get(0).getAutor(), "Orwell");
    }

    @Test
    public void testBuscarLibroPorAutorInexistente() {
        List<Libro> resultados = logica.buscarLibroPorCriterio("Autor", "NoExiste");
        assertTrue(resultados.isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBuscarLibroConValorVacio() {
        logica.buscarLibroPorCriterio("Nombre", "   ");
    }
}