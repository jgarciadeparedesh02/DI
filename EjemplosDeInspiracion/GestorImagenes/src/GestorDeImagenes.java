import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestorDeImagenes extends JFrame implements ActionListener {
    private List<ImageIcon> imagenes;
    private int indiceImagenActual;
    private JLabel etiquetaImagen;
    private JButton botonAnterior;
    private JButton botonSiguiente;

    public GestorDeImagenes() {
        // Configuración de la ventana
        setTitle("Gestor de Imágenes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar la lista de imágenes
        imagenes = new ArrayList<>();
        cargarImagenes();

        // Inicializar el índice de la imagen actual
        indiceImagenActual = 0;

        // Crear etiqueta para mostrar la imagen
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        mostrarImagenActual();

        // Crear botones de navegación
        botonAnterior = new JButton("Anterior");
        botonSiguiente = new JButton("Siguiente");

        botonAnterior.addActionListener(this);
        botonSiguiente.addActionListener(this);

        // Crear panel para organizar componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(etiquetaImagen, BorderLayout.CENTER);
        panel.add(botonAnterior, BorderLayout.WEST);
        panel.add(botonSiguiente, BorderLayout.EAST);

        // Agregar panel a la ventana
        add(panel);

        setVisible(true);
    }

    private void cargarImagenes() {
        // Agrega las rutas de las imágenes a la lista
        imagenes.add(new ImageIcon("imagen1.jpg"));
        imagenes.add(new ImageIcon("imagen2.jpg"));
        // Agrega más imágenes según sea necesario
    }

    private void mostrarImagenActual() {
        ImageIcon imagenActual = imagenes.get(indiceImagenActual);
        etiquetaImagen.setIcon(imagenActual);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonAnterior) {
            indiceImagenActual = (indiceImagenActual - 1 + imagenes.size()) % imagenes.size();
            mostrarImagenActual();
        } else if (e.getSource() == botonSiguiente) {
            indiceImagenActual = (indiceImagenActual + 1) % imagenes.size();
            mostrarImagenActual();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorDeImagenes());
    }
}
