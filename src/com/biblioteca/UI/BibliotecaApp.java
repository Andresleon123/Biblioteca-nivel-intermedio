
package com.biblioteca.UI;

import javax.swing.*;
import java.awt.*;

public class BibliotecaApp extends JFrame {
    private JButton btnAgregar, btnListar, btnBuscar, btnActualizar, btnEliminar;
    private JButton btnContarPorAutor, btnFiltrarPorGenero, btnMostrarNoDisponibles;
    private JTextArea textArea;
    private JTextField txtNombre, txtAutor, txtEditorial, txtFecha, txtGenero;
    private LogicaBiblioteca logica;
    private JCheckBox chkDisponible;
    private GridBagConstraints gbc;

    public BibliotecaApp() {
        logica = new LogicaBiblioteca();
        configurarVentana();
        JPanel panel = crearPanel();
        inicializarComponentes();
        agregarComponentes(panel);
        agregarAcciones();
        add(panel);
    }

    private void configurarVentana() {
        setTitle("Biblioteca");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel crearPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes uniformes
        return panel;
    }

    private void inicializarComponentes() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado uniforme

        txtNombre = new JTextField(15);
        txtAutor = new JTextField(15);
        txtEditorial = new JTextField(15);
        txtFecha = new JTextField(15);
        txtGenero = new JTextField(15);

        chkDisponible = new JCheckBox("Disponible");

        btnAgregar = new JButton("Agregar");
        btnListar = new JButton("Listar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        btnContarPorAutor = new JButton("Contar por Autor");
        btnFiltrarPorGenero = new JButton("Filtrar por Género");
        btnMostrarNoDisponibles = new JButton("Mostrar No Disponibles");
    }

    private void agregarComponentes(JPanel panel) {
        agregarCampo(panel, "Nombre:", txtNombre, 0);
        agregarCampoConCheckbox(panel, "Autor:", txtAutor, chkDisponible, 1);
        agregarCampo(panel, "Editorial:", txtEditorial, 2);
        agregarCampo(panel, "Año:", txtFecha, 3);
        agregarCampo(panel, "Género:", txtGenero, 4);
        agregarTextArea(panel);
        crearPanelBotones(panel);
    }

    private void agregarCampo(JPanel panel, String label, JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void agregarCampoConCheckbox(JPanel panel, String label, JTextField textField, JCheckBox checkBox, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(textField, gbc);
        gbc.gridx = 2;
        panel.add(checkBox, gbc);
    }

    private void agregarTextArea(JPanel panel) {
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
    }

    private void crearPanelBotones(JPanel panel) {
        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 10, 10)); // 2 filas, 4 columnas con espacio

        // Agregamos los botones al panel
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnContarPorAutor);
        panelBotones.add(btnFiltrarPorGenero);
        panelBotones.add(btnMostrarNoDisponibles);

        // Ubicación del panel en la interfaz
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panelBotones, gbc);
    }

    private void agregarAcciones() {
        btnAgregar.addActionListener(e -> logica.agregarLibro(txtNombre, txtAutor, txtEditorial, txtFecha, txtGenero, chkDisponible, textArea));
        btnListar.addActionListener(e -> logica.listarLibros(textArea));
        btnBuscar.addActionListener(e -> logica.buscarLibro(textArea));
        btnActualizar.addActionListener(e -> logica.actualizarLibro(txtNombre, textArea));
        btnEliminar.addActionListener(e -> logica.eliminarLibro(textArea));
        btnContarPorAutor.addActionListener(e -> logica.contarLibrosPorAutor(textArea));
        btnFiltrarPorGenero.addActionListener(e -> logica.filtrarLibrosPorGenero(textArea));
        btnMostrarNoDisponibles.addActionListener(e -> logica.mostrarLibrosNoDisponibles(textArea));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaApp().setVisible(true));
    }
}
