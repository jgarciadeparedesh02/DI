import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuExampleWithSubMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Menú con Submenú");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JMenuBar menuBar = new JMenuBar();

        // Menú "Archivo" con elementos "Abrir" y "Salir"
        JMenu fileMenu = new JMenu("Archivo");

        JMenuItem openItem = new JMenuItem("Abrir");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Has seleccionado Abrir.");
            }
        });

        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(exitItem);

        // Submenú "Editar" con opciones "Copiar" y "Pegar"
        JMenu editMenu = new JMenu("Editar");

        JMenuItem copyItem = new JMenuItem("Copiar");
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Has seleccionado Copiar.");
            }
        });

        JMenuItem pasteItem = new JMenuItem("Pegar");
        pasteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Has seleccionado Pegar.");
            }
        });

        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Agregar los menús al menú principal "Archivo"
        fileMenu.addSeparator(); // Agregamos una línea separadora
        fileMenu.add(editMenu);

        // Agregar el menú principal "Archivo" a la barra de menú
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
