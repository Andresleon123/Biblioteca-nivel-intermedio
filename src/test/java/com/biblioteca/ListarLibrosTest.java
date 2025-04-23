package com.biblioteca;

import com.biblioteca.UI.LogicaBiblioteca;
import com.biblioteca.modelo.Libro;
import com.biblioteca.servicios.GestorBiblioteca;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ListarLibrosTest {

    private LogicaBiblioteca logica;
    private JTextArea textAreaMock;
    private GestorBiblioteca gestorMock;

    @BeforeMethod
    public void setUp() {
        // Mock de GestorBiblioteca con una Biblioteca vacía
        gestorMock = mock(GestorBiblioteca.class);
        textAreaMock = mock(JTextArea.class);

        // Inyectar el gestor mock en una subclase de LogicaBiblioteca
        logica = new LogicaBiblioteca() {
            {
                this.gestor = gestorMock; // Inyección directa del mock
            }
        };
    }

    @Test
    public void listarLibros_CuandoHayLibros_DeberiaMostrarlosEnElTextArea() {
        // Simular lista de libros
        Libro libro1 = new Libro("A", "Autor1", "Editorial1", 2001, "Ficción", true);
        Libro libro2 = new Libro("B", "Autor2", "Editorial2", 2002, "Terror", false);
        when(gestorMock.obtenerListaDeLibros()).thenReturn(Arrays.asList(libro1, libro2));

        // Ejecutar
        logica.listarLibros(textAreaMock);

        // Verificar que el encabezado y libros se imprimen
        verify(textAreaMock).setText("Libros en la biblioteca:\n");
        verify(textAreaMock, atLeastOnce()).append(contains("A")); // Verifica que el libro A se muestra
        verify(textAreaMock, atLeastOnce()).append(contains("B")); // Verifica que el libro B se muestra
    }

    @Test
    public void listarLibros_CuandoNoHayLibros_DeberiaMostrarMensajeDeVacio() {
        when(gestorMock.obtenerListaDeLibros()).thenReturn(Arrays.asList());

        logica.listarLibros(textAreaMock);

        verify(textAreaMock).setText("Libros en la biblioteca:\n");
        verify(textAreaMock).append("No hay libros en la biblioteca.\n");
    }
}
