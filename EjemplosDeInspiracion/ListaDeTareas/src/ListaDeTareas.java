import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDeTareas extends JFrame implements ActionListener {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaTareas;
    private JTextField tareaNueva;

    public ListaDeTareas() {
        // Configuración de la ventana
        setTitle("Lista de Tareas");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear modelo de lista y lista de tareas
        modeloLista = new DefaultListModel<>();
        listaTareas = new JList<>(modeloLista);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Crear campo de texto para agregar tareas
        tareaNueva = new JTextField();
        tareaNueva.addActionListener(this);

        // Crear botones para agregar y eliminar tareas
        JButton agregarButton = new JButton("Agregar");
        JButton eliminarButton = new JButton("Eliminar");

        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);

        // Crear paneles para organizar componentes
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2));
        panelBotones.add(agregarButton);
        panelBotones.add(eliminarButton);

        panelSuperior.add(tareaNueva, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.EAST);

        // Agregar componentes a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JTextField) {
            // Si se presiona "Enter" en el campo de texto, agregar la tarea
            String nuevaTarea = tareaNueva.getText().trim();
            if (!nuevaTarea.isEmpty()) {
                modeloLista.addElement(nuevaTarea);
                tareaNueva.setText("");
            }
        } else if (e.getActionCommand().equals("Agregar")) {
            // Si se presiona el botón "Agregar", agregar la tarea
            String nuevaTarea = tareaNueva.getText().trim();
            if (!nuevaTarea.isEmpty()) {
                modeloLista.addElement(nuevaTarea);
                tareaNueva.setText("");
            }
        } else if (e.getActionCommand().equals("Eliminar")) {
            // Si se presiona el botón "Eliminar", eliminar la tarea seleccionada
            int indiceSeleccionado = listaTareas.getSelectedIndex();
            if (indiceSeleccionado != -1) {
                modeloLista.remove(indiceSeleccionado);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaDeTareas());
    }
}
