package com.biblioteca;
import com.biblioteca.UI.LogicaBiblioteca;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;


import javax.swing.*;

public class BibliotecaTest {

    private LogicaBiblioteca logica;

    private JTextArea textAreaMock;
    
    @BeforeMethod
    public void setUp() {
        logica = new LogicaBiblioteca();
        textAreaMock = mock(JTextArea.class);
    }

    @Test
    public void agregarLibro_DatosValidos_DeberiaAgregar() {
        // Configurar mocks
        JTextField txtNombre = mockTextField("Cien años de soledad");
        JTextField txtAutor = mockTextField("Gabriel García Márquez");
        JTextField txtEditorial = mockTextField("Sudamericana");
        JTextField txtFecha = mockTextField("1967");
        JTextField txtGenero = mockTextField("Realismo mágico");
        JCheckBox chkDisponible = mockCheckBox(true);

        // Ejecutar
        logica.agregarLibro(txtNombre, txtAutor, txtEditorial,
                txtFecha, txtGenero, chkDisponible, textAreaMock);

        // Verificar que se haya establecido el mensaje de éxito
        verify(textAreaMock).setText(contains("Libro agregado exitosamente."));
    }

    @Test
    public void agregarLibro_NombreVacio_DeberiaMostrarError() {
              // Configurar mocks
        JTextField txtNombre = mockTextField(""); // Nombre vacío

        // Ejecutar
        logica.agregarLibro(txtNombre, mockTextField("Autor"), mockTextField("Editorial"),
                mockTextField("2020"), mockTextField("Género"),
                mockCheckBox(true), textAreaMock);

        // Verificar que se haya establecido el mensaje de error
        verify(textAreaMock).setText(contains("Error: Los campos no pueden estar vacíos."));
    }
    
    // Métodos helper
    private JTextField mockTextField(String text) {
        JTextField mock = mock(JTextField.class);
        when(mock.getText()).thenReturn(text);
        return mock;
    }
    
    private JCheckBox mockCheckBox(boolean selected) {
        JCheckBox mock = mock(JCheckBox.class);
        when(mock.isSelected()).thenReturn(selected);
        return mock;
    }
}