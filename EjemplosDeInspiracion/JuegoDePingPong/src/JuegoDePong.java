import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JuegoDePong extends JPanel implements ActionListener, KeyListener {
    private static final int ANCHO_VENTANA = 400;
    private static final int ALTO_VENTANA = 300;
    private static final int ANCHO_PALETA = 10;
    private static final int ALTO_PALETA = 60;
    private static final int ANCHO_BOLA = 10;
    private static final int ALTO_BOLA = 10;
    private static final int VELOCIDAD_PALETA = 3;
    private static final int VELOCIDAD_BOLA = 2;

    private int jugadorY;
    private int computadoraY;
    private int bolaX;
    private int bolaY;
    private int direccionBolaX;
    private int direccionBolaY;
    private int puntajeJugador;
    private int puntajeComputadora;
    private boolean enJuego;

    public JuegoDePong() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        jugadorY = ALTO_VENTANA / 2 - ALTO_PALETA / 2;
        computadoraY = ALTO_VENTANA / 2 - ALTO_PALETA / 2;
        bolaX = ANCHO_VENTANA / 2 - ANCHO_BOLA / 2;
        bolaY = ALTO_VENTANA / 2 - ALTO_BOLA / 2;
        direccionBolaX = 1;
        direccionBolaY = 1;
        puntajeJugador = 0;
        puntajeComputadora = 0;
        enJuego = true;

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enJuego) {
            moverPaletaComputadora();
            moverBola();
            verificarColisiones();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar paletas
        g.setColor(Color.WHITE);
        g.fillRect(10, jugadorY, ANCHO_PALETA, ALTO_PALETA);
        g.fillRect(ANCHO_VENTANA - 20, computadoraY, ANCHO_PALETA, ALTO_PALETA);

        // Dibujar bola
        g.fillRect(bolaX, bolaY, ANCHO_BOLA, ALTO_BOLA);

        // Dibujar puntaje
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Jugador: " + puntajeJugador, 50, 30);
        g.drawString("Computadora: " + puntajeComputadora, ANCHO_VENTANA - 200, 30);

        // Dibujar línea central
        for (int i = 0; i < ALTO_VENTANA; i += 20) {
            g.fillRect(ANCHO_VENTANA / 2 - 2, i, 4, 10);
        }

        // Verificar si el juego ha terminado
        if (puntajeJugador >= 5) {
            g.setColor(Color.GREEN);
            g.drawString("¡Jugador Gana!", ANCHO_VENTANA / 2 - 80, ALTO_VENTANA / 2);
            enJuego = false;
        } else if (puntajeComputadora >= 5) {
            g.setColor(Color.RED);
            g.drawString("¡Computadora Gana!", ANCHO_VENTANA / 2 - 100, ALTO_VENTANA / 2);
            enJuego = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP && jugadorY > 0) {
            jugadorY -= VELOCIDAD_PALETA;
        } else if (keyCode == KeyEvent.VK_DOWN && jugadorY < ALTO_VENTANA - ALTO_PALETA) {
            jugadorY += VELOCIDAD_PALETA;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void moverPaletaComputadora() {
        int centroPaleta = computadoraY + ALTO_PALETA / 2;
        if (centroPaleta < bolaY - ALTO_PALETA / 4) {
            computadoraY += VELOCIDAD_PALETA;
        } else if (centroPaleta > bolaY + ALTO_PALETA / 4) {
            computadoraY -= VELOCIDAD_PALETA;
        }
    }

    private void moverBola() {
        bolaX += direccionBolaX * VELOCIDAD_BOLA;
        bolaY += direccionBolaY * VELOCIDAD_BOLA;

        // Cambiar dirección si la bola choca con la parte superior o inferior
        if (bolaY <= 0 || bolaY >= ALTO_VENTANA - ALTO_BOLA) {
            direccionBolaY *= -1;
        }

        // Verificar si la bola choca con una paleta
        if (bolaX <= 20 + ANCHO_PALETA && bolaY >= jugadorY && bolaY <= jugadorY + ALTO_PALETA) {
            direccionBolaX *= -1;
            puntajeJugador++;
        } else if (bolaX >= ANCHO_VENTANA - 30 - ANCHO_PALETA && bolaY >= computadoraY && bolaY <= computadoraY + ALTO_PALETA) {
            direccionBolaX *= -1;
            puntajeComputadora++;
        }

        // Verificar si la bola sale del área de juego
        if (bolaX <= 0 || bolaX >= ANCHO_VENTANA - ANCHO_BOLA) {
            enJuego = false;
        }
    }

    private void verificarColisiones() {
        // No se necesitan colisiones adicionales en este juego simple
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JuegoDePong());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
