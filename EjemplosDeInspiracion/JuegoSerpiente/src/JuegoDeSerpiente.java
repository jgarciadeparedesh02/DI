import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoDeSerpiente extends JPanel implements ActionListener {
    private static final int ANCHO_VENTANA = 400;
    private static final int ALTO_VENTANA = 400;
    private static final int TAMANO_CELDA = 20;
    private static final int VELOCIDAD = 150; // Milisegundos por movimiento

    private List<Point> serpiente;
    private Point comida;
    private int direccion; // 0: Arriba, 1: Derecha, 2: Abajo, 3: Izquierda
    private boolean enJuego;
    private Timer timer;

    public JuegoDeSerpiente() {
        serpiente = new ArrayList<>();
        comida = new Point();
        direccion = 1; // Comenzar moviéndose hacia la derecha
        enJuego = true;

        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cambiarDireccion(e.getKeyCode());
            }
        });

        iniciarJuego();
    }

    private void iniciarJuego() {
        serpiente.clear();
        serpiente.add(new Point(5, 5)); // Posición inicial de la serpiente
        generarComida();

        timer = new Timer(VELOCIDAD, this);
        timer.start();
    }

    private void generarComida() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(ANCHO_VENTANA / TAMANO_CELDA);
            y = random.nextInt(ALTO_VENTANA / TAMANO_CELDA);
            comida.setLocation(x, y);
        } while (serpiente.contains(comida));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enJuego) {
            moverSerpiente();
            verificarColisiones();
            repaint();
        }
    }

    private void moverSerpiente() {
        Point cabeza = serpiente.get(0);
        Point nuevaCabeza = (Point) cabeza.clone();

        switch (direccion) {
            case 0: // Arriba
                nuevaCabeza.translate(0, -1);
                break;
            case 1: // Derecha
                nuevaCabeza.translate(1, 0);
                break;
            case 2: // Abajo
                nuevaCabeza.translate(0, 1);
                break;
            case 3: // Izquierda
                nuevaCabeza.translate(-1, 0);
                break;
        }

        serpiente.add(0, nuevaCabeza);

        if (nuevaCabeza.equals(comida)) {
            generarComida();
        } else {
            serpiente.remove(serpiente.size() - 1); // Eliminar la cola
        }
    }

    private void verificarColisiones() {
        Point cabeza = serpiente.get(0);

        // Colisión con bordes
        if (cabeza.x < 0 || cabeza.x >= ANCHO_VENTANA / TAMANO_CELDA
                || cabeza.y < 0 || cabeza.y >= ALTO_VENTANA / TAMANO_CELDA) {
            enJuego = false;
            timer.stop();
            JOptionPane.showMessageDialog(this, "Juego terminado. Puntuación: " + (serpiente.size() - 1));
        }

        // Colisión con la propia serpiente
        for (int i = 1; i < serpiente.size(); i++) {
            if (cabeza.equals(serpiente.get(i))) {
                enJuego = false;
                timer.stop();
                JOptionPane.showMessageDialog(this, "Juego terminado. Puntuación: " + (serpiente.size() - 1));
                break;
            }
        }
    }

    private void cambiarDireccion(int nuevaDireccion) {
        if (nuevaDireccion == KeyEvent.VK_UP && direccion != 2) {
            direccion = 0; // Arriba
        } else if (nuevaDireccion == KeyEvent.VK_RIGHT && direccion != 3) {
            direccion = 1; // Derecha
        } else if (nuevaDireccion == KeyEvent.VK_DOWN && direccion != 0) {
            direccion = 2; // Abajo
        } else if (nuevaDireccion == KeyEvent.VK_LEFT && direccion != 1) {
            direccion = 3; // Izquierda
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar comida
        g.setColor(Color.RED);
        g.fillRect(comida.x * TAMANO_CELDA, comida.y * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);

        // Dibujar serpiente
        g.setColor(Color.GREEN);
        for (Point punto : serpiente) {
            g.fillRect(punto.x * TAMANO_CELDA, punto.y * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Serpiente");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JuegoDeSerpiente());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
