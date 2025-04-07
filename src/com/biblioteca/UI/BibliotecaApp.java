package com.biblioteca.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BibliotecaApp extends JFrame {
    private JButton btnAgregar, btnListar, btnBuscar, btnActualizar, btnEliminar, btnGuardar, btnCargar;
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
        logica.cargarLibros(textArea);
    }

    private void configurarVentana() {
        setTitle("Biblioteca");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {//realizar eventos segun lo que haga la ventana
            @Override
            public void windowClosing(WindowEvent e) {//Se ejevcuta cuando la ventana se esta cerrando
                logica.guardarLibros(textArea);//guarda los libros archivo txt
                super.windowClosing(e);//se cierra la ventana
            }
        });
    }

    private JPanel crearPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private void inicializarComponentes() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        txtNombre = new JTextField(18);
        txtAutor = new JTextField(18);
        txtEditorial = new JTextField(18);
        txtFecha = new JTextField(18);
        txtGenero = new JTextField(18);

        chkDisponible = new JCheckBox("Disponible");

        btnAgregar = new JButton("Agregar");
        btnListar = new JButton("Listar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        btnCargar = new JButton("Cargar");

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
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private void agregarCampoConCheckbox(JPanel panel, String label, JTextField textField, JCheckBox checkBox, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
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
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 10, 10)); // 2 filas, 5 columnas

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnContarPorAutor);
        panelBotones.add(btnFiltrarPorGenero);
        panelBotones.add(btnMostrarNoDisponibles);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);

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
        btnGuardar.addActionListener(e -> logica.guardarLibros(textArea));
        btnCargar.addActionListener(e -> logica.cargarLibros(textArea));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaApp().setVisible(true));
    }
}
