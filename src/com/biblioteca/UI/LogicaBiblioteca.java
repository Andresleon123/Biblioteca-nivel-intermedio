package com.biblioteca.UI;

import com.biblioteca.modelo.Libro;
import com.biblioteca.servicios.Biblioteca;
import com.biblioteca.servicios.GestorBiblioteca;
import com.biblioteca.util.Constantes;

import javax.swing.*;
import java.util.List;

public class LogicaBiblioteca {
    private GestorBiblioteca gestor;


    public LogicaBiblioteca() {
        gestor = new GestorBiblioteca(new Biblioteca());

    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void agregarLibro(JTextField txtNombre, JTextField txtAutor, JTextField txtEditorial, JTextField txtfecha, JTextField txtGenero, JCheckBox chkDisponible, JTextArea textArea) {
        try {
            String nombre = txtNombre.getText().trim();
            String autor = txtAutor.getText().trim();
            String editorial = txtEditorial.getText().trim();
            int fecha = Integer.parseInt(txtfecha.getText().trim());
            String genero = txtGenero.getText().trim(); // Obtener el género
            boolean disponible = chkDisponible.isSelected(); // Obtener la disponibilidad

            // Crear el libro con los nuevos atributos
            String resultado = gestor.agregarLibro(nombre, autor, editorial, fecha, genero, disponible);
            mostrarMensaje(resultado);
            limpiarCampos(txtNombre, txtAutor, txtEditorial, txtfecha, txtGenero, chkDisponible);
            mostrarLibrosDisponibles(textArea);
            mostrarLibrosNoDisponibles(textArea);

        } catch (NumberFormatException e) {
            mostrarMensaje(Constantes.ERROR_NUMERO_VALIDO);
        } catch (Exception e) {
            mostrarMensaje(Constantes.ERROR_INESPERADO);
        }
    }

    public void listarLibros(JTextArea textArea) {
        textArea.setText("Libros en la biblioteca:\n");
        List<Libro> libros = gestor.obtenerListaDeLibros();

        if (libros == null || libros.isEmpty()) {
            textArea.append("No hay libros en la biblioteca.\n");
        }

        StringBuilder listaLibros = new StringBuilder();
        for (Libro libro : libros) {
            listaLibros.append(libro.toString()).append("\n");
        }
        textArea.append(listaLibros.toString());
    }

    public void buscarLibro(JTextArea textArea) {
        String[] opciones = {"Nombre", "Autor", "Editorial", "Año"};
        String criterio = (String) JOptionPane.showInputDialog(
                null, Constantes.BUSQUEDA,
                "Buscar libro", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        String valorBuscado = JOptionPane.showInputDialog(null, Constantes.BUSQUEDA).trim();
        if (valorBuscado.isEmpty()) {
            mostrarMensaje(Constantes.ERROR_LIBRO_NO_ENCONTRADO);
        }

        List<Libro> resultados = gestor.buscarLibrosPorNAEF(criterio, valorBuscado);

        if (resultados.isEmpty()) {
            mostrarMensaje("No se encontró el libro con \"" + criterio + "\": " + valorBuscado);
        } else {
            StringBuilder mensaje = new StringBuilder("Libros encontrados:\n");
            resultados.forEach(libro -> mensaje.append(libro).append("\n"));
            mostrarMensaje(mensaje.toString());
        }
    }

    public void actualizarLibro(JTextField txtNombre, JTextArea textArea) {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarMensaje(Constantes.NOMBRE_OBLIGATORIO);// Salir del método si el nombre está vacío
        }

        String[] opciones = {"Nombre", "Autor", "Editorial", "Año"};
        String campo = (String) JOptionPane.showInputDialog(
                null, "Seleccione el campo a actualizar:",
                "Actualizar libro", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        try {
            if (campo != null) { // Verificar que el usuario no haya cancelado
                String nuevoValor = JOptionPane.showInputDialog(null, Constantes.ACTUALIZAR);
                if (nuevoValor != null) {
                    switch (campo) {
                        case "Nombre":
                            actualizarCampo(nombre, nuevoValor, "nombre");
                            break;
                        case "Autor":
                            actualizarCampo(nombre, nuevoValor, "autor");
                            break;
                        case "Editorial":
                            actualizarCampo(nombre, nuevoValor, "editorial");
                            break;
                        case "Año":
                            int nuevoAño = Integer.parseInt(nuevoValor);
                            gestor.actualizarFechaLibro(nombre, nuevoAño); // Cambiado a 'gestor'
                            mostrarMensaje(Constantes.CONFIRMACION_DE_ACTUALIZACION);
                            break;
                        default:
                            mostrarMensaje(Constantes.ERROR_LIBRO_NO_ENCONTRADO);
                            break;
                    }
                }
            }
            listarLibros(textArea);
        } catch (NumberFormatException e) {
            mostrarMensaje(Constantes.ERROR_NUMERO_VALIDO);
        } catch (IllegalArgumentException e) {
            mostrarMensaje(Constantes.ERROR_INESPERADO);
        }
    }

    private void actualizarCampo(String nombre, String nuevoValor, String queCampoActualizara) {
        switch (queCampoActualizara) {
            case "nombre":
                gestor.actualizarNombreLibro(nombre, nuevoValor); // Cambiado a 'gestor'
                break;
            case "autor":
                gestor.actualizarAutorLibro(nombre, nuevoValor); // Cambiado a 'gestor'
                break;
            case "editorial":
                gestor.actualizarEditorialLibro(nombre, nuevoValor); // Cambiado a 'gestor'
                break;
            default:
                mostrarMensaje(Constantes.NO_SE_ACTUALIZARON_LOS_CAMBIOS);
                break;
        }
        mostrarMensaje(Constantes.CONFIRMACION_DE_ACTUALIZACION);
    }

    public void eliminarLibro(JTextArea textArea) {
        String nombre = JOptionPane.showInputDialog(null, Constantes.NOMBRE_LIBRO_A_ELIMINAR);
        if (nombre != null && !nombre.trim().isEmpty()) {
            gestor.eliminarLibroPorNombre(nombre);
            mostrarMensaje(Constantes.EXITO_ELIMINAR_LIBRO);
            listarLibros(textArea);
        }
    }
    public void mostrarLibrosDisponibles(JTextArea textArea) {
        List<Libro> librosDisponibles = gestor.listarLibrosDisponibles();
        textArea.setText("Libros Disponibles:\n");
        for (Libro libro : librosDisponibles) {
            textArea.append(libro.toString() + "\n");
        }
    }
    public void mostrarLibrosNoDisponibles(JTextArea textArea) {
        List<Libro> librosNoDisponibles = gestor.listarLibrosNoDisponibles();
        textArea.setText("Libros No Disponibles:\n");
        if (librosNoDisponibles.isEmpty()) {
            textArea.append("No hay libros no disponibles.\n");
        } else {
            for (Libro libro : librosNoDisponibles) {
                textArea.append(libro.toString() + "\n");
            }
        }
    }
    public void contarLibrosPorAutor(JTextArea textArea) {
        String autor = JOptionPane.showInputDialog("Ingrese el nombre del autor:");
        if (autor != null && !autor.trim().isEmpty()) {
            long cantidad = gestor.contarLibrosPorAutor(autor);
            textArea.setText("Cantidad de libros de " + autor + ": " + cantidad);
        } else {
            JOptionPane.showMessageDialog(null, Constantes.ERROR_CAMPOS_VACIOS);
        }
    }
    public void filtrarLibrosPorGenero(JTextArea textArea) {
        String genero = JOptionPane.showInputDialog("Ingrese el género:");
        if (genero != null && !genero.trim().isEmpty()) {
            List<Libro> librosFiltrados = gestor.filtrarLibrosPorGenero(genero);
            textArea.setText("Libros del género " + genero + ":\n");
            if (librosFiltrados.isEmpty()) {
                textArea.append("No se encontraron libros de este género.\n");
            } else {
                for (Libro libro : librosFiltrados) {
                    textArea.append(libro.toString() + "\n");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, Constantes.ERROR_CAMPOS_VACIOS);
        }
    }


    private void limpiarCampos(JTextField txtNombre, JTextField txtAutor, JTextField txtEditorial, JTextField txtfecha, JTextField txtGenero, JCheckBox chkDisponible) {
        txtNombre.setText("");
        txtAutor.setText("");
        txtEditorial.setText("");
        txtfecha.setText("");
        txtGenero.setText(""); // Limpiar el campo de género
        chkDisponible.setSelected(false); // Reiniciar el JCheckBox
    }
}
