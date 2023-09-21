import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeRegistroDeUsuarios extends JFrame implements ActionListener {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton botonRegistrar;
    private JTextArea areaUsuariosRegistrados;
    private List<Usuario> usuariosRegistrados;

    public SistemaDeRegistroDeUsuarios() {
        // Configuración de la ventana
        setTitle("Sistema de Registro de Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear campos de texto
        campoUsuario = new JTextField(20);
        campoContraseña = new JPasswordField(20);

        // Crear botón de registro
        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);

        // Crear área de texto para mostrar usuarios registrados
        areaUsuariosRegistrados = new JTextArea(10, 30);
        areaUsuariosRegistrados.setEditable(false);

        // Crear lista de usuarios registrados
        usuariosRegistrados = new ArrayList<>();

        // Crear paneles para organizar componentes
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridLayout(3, 2));
        panelRegistro.add(new JLabel("Nombre de Usuario:"));
        panelRegistro.add(campoUsuario);
        panelRegistro.add(new JLabel("Contraseña:"));
        panelRegistro.add(campoContraseña);
        panelRegistro.add(new JLabel(""));
        panelRegistro.add(botonRegistrar);

        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setLayout(new BorderLayout());
        panelUsuarios.add(new JLabel("Usuarios Registrados:"), BorderLayout.NORTH);
        panelUsuarios.add(new JScrollPane(areaUsuariosRegistrados), BorderLayout.CENTER);

        // Agregar componentes a la ventana
        add(panelRegistro, BorderLayout.NORTH);
        add(panelUsuarios, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            registrarUsuario();
        }
    }

    private void registrarUsuario() {
        String nombreUsuario = campoUsuario.getText();
        String contraseña = new String(campoContraseña.getPassword());

        if (!nombreUsuario.isEmpty() && !contraseña.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña);
            usuariosRegistrados.add(nuevoUsuario);
            actualizarListaUsuarios();
            campoUsuario.setText("");
            campoContraseña.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarListaUsuarios() {
        areaUsuariosRegistrados.setText("");
        for (Usuario usuario : usuariosRegistrados) {
            areaUsuariosRegistrados.append(usuario.getNombreUsuario() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaDeRegistroDeUsuarios());
    }
}

class Usuario {
    private String nombreUsuario;
    private String contraseña;

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }
}
