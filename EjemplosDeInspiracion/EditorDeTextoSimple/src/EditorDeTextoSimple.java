import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditorDeTextoSimple extends JFrame implements ActionListener {
    private JTextArea areaDeTexto;
    private JFileChooser selectorDeArchivos;
    private String archivoActual;

    public EditorDeTextoSimple() {
        // Configuración de la ventana
        setTitle("Editor de Texto Simple");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear área de texto
        areaDeTexto = new JTextArea();
        areaDeTexto.setFont(new Font("Arial", Font.PLAIN, 14));

        // Crear barra de desplazamiento para el área de texto
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);

        // Crear selector de archivos
        selectorDeArchivos = new JFileChooser();
        selectorDeArchivos.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));

        // Crear menú y elementos del menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemGuardar = new JMenuItem("Guardar");

        menuItemAbrir.addActionListener(this);
        menuItemGuardar.addActionListener(this);

        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemGuardar);
        menuBar.add(menuArchivo);

        setJMenuBar(menuBar);

        // Agregar el área de texto al contenido de la ventana
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Abrir")) {
            abrirArchivo();
        } else if (e.getActionCommand().equals("Guardar")) {
            guardarArchivo();
        }
    }

    private void abrirArchivo() {
        int resultado = selectorDeArchivos.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorDeArchivos.getSelectedFile();
            archivoActual = archivoSeleccionado.getAbsolutePath();

            try {
                FileReader lector = new FileReader(archivoSeleccionado);
                BufferedReader br = new BufferedReader(lector);

                String linea;
                areaDeTexto.setText(""); // Limpiar el área de texto antes de cargar el archivo

                while ((linea = br.readLine()) != null) {
                    areaDeTexto.append(linea + "\n");
                }

                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarArchivo() {
        if (archivoActual != null) {
            try {
                FileWriter escritor = new FileWriter(archivoActual);
                BufferedWriter bw = new BufferedWriter(escritor);

                bw.write(areaDeTexto.getText());

                bw.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            int resultado = selectorDeArchivos.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = selectorDeArchivos.getSelectedFile();
                archivoActual = archivoSeleccionado.getAbsolutePath();

                try {
                    FileWriter escritor = new FileWriter(archivoSeleccionado);
                    BufferedWriter bw = new BufferedWriter(escritor);

                    bw.write(areaDeTexto.getText());

                    bw.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorDeTextoSimple());
    }
}
