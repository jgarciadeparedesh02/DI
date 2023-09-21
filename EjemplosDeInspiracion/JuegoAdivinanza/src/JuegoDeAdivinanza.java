import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoDeAdivinanza extends JFrame implements ActionListener {
    private static final String[] PALABRAS = {"COCHE", "CASA", "ORDENADOR", "PROGRAMACION", "JAVA", "DEVELOPER"};
    private String palabraSecreta;
    private StringBuilder palabraAdivinada;
    private JLabel etiquetaPalabra;
    private JLabel etiquetaIntentos;
    private JButton botonAdivinar;
    private int intentosRestantes;

    public JuegoDeAdivinanza() {
        // Configuración de la ventana
        setTitle("Juego de Adivinanza");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el juego
        elegirPalabraSecreta();
        palabraAdivinada = new StringBuilder("_".repeat(palabraSecreta.length()));
        intentosRestantes = 6;

        // Crear etiquetas para mostrar la palabra a adivinar y los intentos restantes
        etiquetaPalabra = new JLabel(palabraAdivinada.toString());
        etiquetaPalabra.setFont(new Font("Arial", Font.PLAIN, 24));
        etiquetaIntentos = new JLabel("Intentos restantes: " + intentosRestantes);

        // Crear botón para adivinar una letra
        botonAdivinar = new JButton("Adivinar letra");
        botonAdivinar.addActionListener(this);

        // Crear panel para organizar componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(etiquetaPalabra);
        panel.add(etiquetaIntentos);
        panel.add(botonAdivinar);

        // Agregar panel a la ventana
        add(panel);

        setVisible(true);
    }

    private void elegirPalabraSecreta() {
        Random random = new Random();
        palabraSecreta = PALABRAS[random.nextInt(PALABRAS.length)];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonAdivinar) {
            adivinarLetra();
        }
    }

    private void adivinarLetra() {
        String letraAdivinada = JOptionPane.showInputDialog(this, "Adivina una letra:");
        if (letraAdivinada != null && letraAdivinada.length() == 1) {
            char letra = letraAdivinada.charAt(0);
            boolean acertado = false;

            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra) {
                    palabraAdivinada.setCharAt(i, letra);
                    acertado = true;
                }
            }

            if (!acertado) {
                intentosRestantes--;
            }

            etiquetaPalabra.setText(palabraAdivinada.toString());
            etiquetaIntentos.setText("Intentos restantes: " + intentosRestantes);

            if (intentosRestantes <= 0) {
                terminarJuego(false);
            } else if (palabraAdivinada.toString().equals(palabraSecreta)) {
                terminarJuego(true);
            }
        }
    }

    private void terminarJuego(boolean ganado) {
        if (ganado) {
            JOptionPane.showMessageDialog(this, "¡Has adivinado la palabra!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Te has quedado sin intentos. La palabra era: " + palabraSecreta, "Derrota", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoDeAdivinanza());
    }
}
