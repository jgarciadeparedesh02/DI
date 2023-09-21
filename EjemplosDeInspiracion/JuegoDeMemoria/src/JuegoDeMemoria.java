import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JuegoDeMemoria extends JFrame implements ActionListener {
    private static final int NUM_CARTAS = 12;
    private List<Integer> valoresCartas;
    private JButton[] botonesCartas;
    private int primeraCarta = -1;
    private int segundaCarta = -1;
    private Timer temporizador;

    public JuegoDeMemoria() {
        // Configuración de la ventana
        setTitle("Juego de Memoria");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear lista de valores de cartas y barajarla
        valoresCartas = new ArrayList<>();
        for (int i = 1; i <= NUM_CARTAS / 2; i++) {
            valoresCartas.add(i);
            valoresCartas.add(i);
        }
        Collections.shuffle(valoresCartas);

        // Crear botones para las cartas
        botonesCartas = new JButton[NUM_CARTAS];
        for (int i = 0; i < NUM_CARTAS; i++) {
            botonesCartas[i] = new JButton("?");
            botonesCartas[i].setFont(new Font("Arial", Font.PLAIN, 24));
            botonesCartas[i].setActionCommand(String.valueOf(i));
            botonesCartas[i].addActionListener(this);
        }

        // Crear panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 4));
        for (int i = 0; i < NUM_CARTAS; i++) {
            panelBotones.add(botonesCartas[i]);
        }

        // Agregar panel de botones a la ventana
        add(panelBotones);

        // Crear temporizador para ocultar las cartas después de un tiempo
        temporizador = new Timer(1000, e -> ocultarCartas());

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (temporizador.isRunning()) {
            return; // Evitar que el jugador haga clic durante la ocultación
        }

        int indiceCarta = Integer.parseInt(e.getActionCommand());

        // Evitar que el jugador haga clic en la misma carta dos veces
        if (indiceCarta == primeraCarta) {
            return;
        }

        // Mostrar la carta seleccionada
        botonesCartas[indiceCarta].setText(String.valueOf(valoresCartas.get(indiceCarta)));

        if (primeraCarta == -1) {
            primeraCarta = indiceCarta;
        } else {
            segundaCarta = indiceCarta;

            // Comprobar si las cartas coinciden
            if (valoresCartas.get(primeraCarta).equals(valoresCartas.get(segundaCarta))) {
                botonesCartas[primeraCarta].setEnabled(false);
                botonesCartas[segundaCarta].setEnabled(false);
            }

            // Iniciar el temporizador para ocultar las cartas
            temporizador.start();
        }
    }

    private void ocultarCartas() {
        botonesCartas[primeraCarta].setText("?");
        botonesCartas[segundaCarta].setText("?");
        primeraCarta = -1;
        segundaCarta = -1;
        temporizador.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoDeMemoria());
    }
}
