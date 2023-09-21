import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GestorDeContactos extends JFrame implements ActionListener {
    private DefaultTableModel modeloTabla;
    private JTable tablaContactos;
    private JTextField campoNombre, campoEmail;
    private JButton botonAgregar, botonEditar, botonEliminar;

    public GestorDeContactos() {
        // Configuración de la ventana
        setTitle("Gestor de Contactos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear modelo de tabla y tabla de contactos
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Email");
        tablaContactos = new JTable(modeloTabla);

        // Crear campos de texto
        campoNombre = new JTextField(20);
        campoEmail = new JTextField(20);

        // Crear botones de acción
        botonAgregar = new JButton("Agregar");
        botonEditar = new JButton("Editar");
        botonEliminar = new JButton("Eliminar");

        botonAgregar.addActionListener(this);
        botonEditar.addActionListener(this);
        botonEliminar.addActionListener(this);

        // Crear paneles para organizar componentes
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2));
        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Email:"));
        panelSuperior.add(campoEmail);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);

        // Agregar componentes a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaContactos), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonAgregar) {
            agregarContacto();
        } else if (e.getSource() == botonEditar) {
            editarContacto();
        } else if (e.getSource() == botonEliminar) {
            eliminarContacto();
        }
    }

    private void agregarContacto() {
        String nombre = campoNombre.getText();
        String email = campoEmail.getText();

        if (!nombre.isEmpty() && !email.isEmpty()) {
            Vector<String> fila = new Vector<>();
            fila.add(nombre);
            fila.add(email);
            modeloTabla.addRow(fila);
            campoNombre.setText("");
            campoEmail.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese nombre y email.");
        }
    }

    private void editarContacto() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = campoNombre.getText();
            String email = campoEmail.getText();

            if (!nombre.isEmpty() && !email.isEmpty()) {
                modeloTabla.setValueAt(nombre, filaSeleccionada, 0);
                modeloTabla.setValueAt(email, filaSeleccionada, 1);
                campoNombre.setText("");
                campoEmail.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese nombre y email.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para editar.");
        }
    }

    private void eliminarContacto() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada != -1) {
            modeloTabla.removeRow(filaSeleccionada);
            campoNombre.setText("");
            campoEmail.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorDeContactos());
    }
}
