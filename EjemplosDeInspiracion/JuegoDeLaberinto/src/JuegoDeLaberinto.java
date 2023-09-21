import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JuegoDeLaberinto extends JPanel implements ActionListener, KeyListener {
    private static final int ANCHO_VENTANA = 400;
    private static final int ALTO_VENTANA = 400;
    private static final int TAMANO_CELDA = 20;

    private int[][] laberinto = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int jugadorX;
    private int jugadorY;
    private int destinoX;
    private int destinoY;
    private boolean ganaste;

    public JuegoDeLaberinto() {
        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        jugadorX = 1;
        jugadorY = 1;
        destinoX = 8;
        destinoY = 5;
        ganaste = false;

        Timer timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ganaste) {
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el laberinto
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                if (laberinto[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * TAMANO_CELDA, i * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
                }
            }
        }

        // Dibujar el jugador y el destino
        g.setColor(Color.BLUE);
        g.fillRect(jugadorX * TAMANO_CELDA, jugadorY * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
        g.setColor(Color.RED);
        g.fillRect(destinoX * TAMANO_CELDA, destinoY * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);

        // Verificar si el jugador llegó al destino
        if (jugadorX == destinoX && jugadorY == destinoY) {
            ganaste = true;
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("¡Ganaste!", 150, 200);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!ganaste) {
            if (keyCode == KeyEvent.VK_UP && puedeMoverse(jugadorX, jugadorY - 1)) {
                jugadorY--;
            } else if (keyCode == KeyEvent.VK_DOWN && puedeMoverse(jugadorX, jugadorY + 1)) {
                jugadorY++;
            } else if (keyCode == KeyEvent.VK_LEFT && puedeMoverse(jugadorX - 1, jugadorY)) {
                jugadorX--;
            } else if (keyCode == KeyEvent.VK_RIGHT && puedeMoverse(jugadorX + 1, jugadorY)) {
                jugadorX++;
            }
        }
        repaint();
    }

    private boolean puedeMoverse(int x, int y) {
        return x >= 0 && x < laberinto[0].length && y >= 0 && y < laberinto.length && laberinto[y][x] == 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Laberinto");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JuegoDeLaberinto());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
