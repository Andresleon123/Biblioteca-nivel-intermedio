package com.biblioteca.UI;

import javax.swing.*;
import java.awt.*;

public class BibliotecaApp extends JFrame {
    private JButton btnAgregar, btnListar, btnBuscar, btnActualizar, btnEliminar;
    private JTextArea textArea;
    private JTextField txtNombre, txtAutor, txtEditorial, txtFecha;
    private LogicaBiblioteca logica; // Instancia de LogicaBiblioteca
    private GridBagConstraints gbc; // Declarar gbc aquí para que sea accesible en todos los métodos

    public BibliotecaApp() {
        logica = new LogicaBiblioteca(); // Crear instancia de LogicaBiblioteca
        configurarVentana();
        JPanel panel = crearPanel();
        inicializarComponentes();
        agregarComponentes(panel);
        agregarAcciones();
        add(panel);
    }

    private void configurarVentana() {
        setTitle("Biblioteca");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel crearPanel() {
        return new JPanel(new GridBagLayout());
    }

    private void inicializarComponentes() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        txtNombre = new JTextField(15);
        txtAutor = new JTextField(15);
        txtEditorial = new JTextField(15);
        txtFecha = new JTextField(15);

        btnAgregar = new JButton("Agregar");
        btnListar = new JButton("Listar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
    }

    private void agregarComponentes(JPanel panel) {
        agregarCampo(panel, "Nombre:", txtNombre, 0);
        agregarCampo(panel, "Autor:", txtAutor, 1);
        agregarCampo(panel, "Editorial:", txtEditorial, 2);
        agregarCampo(panel, "Año:", txtFecha, 3);
        crearBotones(panel);
        agregarTextArea(panel);
    }

    private void agregarCampo(JPanel panel, String label, JTextField textField, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    private void crearBotones(JPanel panel) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(btnAgregar, gbc);
        gbc.gridx = 1;
        panel.add(btnBuscar, gbc);
        gbc.gridx = 2;
        panel.add(btnActualizar, gbc);
        gbc.gridx = 3;
        panel.add(btnEliminar, gbc);
        gbc.gridx = 4;
        panel.add(btnListar, gbc);
    }

    private void agregarTextArea(JPanel panel) {
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
    }

    private void agregarAcciones() {
        btnAgregar.addActionListener(e -> logica.agregarLibro(txtNombre, txtAutor, txtEditorial, txtFecha, textArea));
        btnListar.addActionListener(e -> logica.listarLibros(textArea));
        btnBuscar.addActionListener(e -> logica.buscarLibro(textArea));
        btnActualizar.addActionListener(e -> logica.actualizarLibro(txtNombre, textArea));
        btnEliminar.addActionListener(e -> logica.eliminarLibro(textArea));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaApp().setVisible(true));
    }
}