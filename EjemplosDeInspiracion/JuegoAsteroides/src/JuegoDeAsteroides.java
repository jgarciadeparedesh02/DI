import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoDeAsteroides extends JFrame implements ActionListener, KeyListener {
    public static int ANCHO_VENTANA = 600;
    public static int ALTO_VENTANA = 400;
    public static int VELOCIDAD_NAVE = 5;
    public static int VELOCIDAD_DISPARO = 10;
    public static int VELOCIDAD_ASTEROIDES = 3;

    private Timer timer;
    private Nave nave;
    private List<Disparo> disparos;
    private List<Asteroide> asteroides;

    public JuegoDeAsteroides() {
        setTitle("Juego de Asteroides");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addKeyListener(this);

        nave = new Nave(ANCHO_VENTANA / 2, ALTO_VENTANA - 50);
        disparos = new ArrayList<>();
        asteroides = new ArrayList<>();

        timer = new Timer(200, this);
        timer.start();

        generarAsteroides();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moverNave();
        moverDisparos();
        moverAsteroides();
        detectarColisiones();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            nave.moverIzquierda();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            nave.moverDerecha();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            disparos.add(new Disparo(nave.getX() + nave.getAncho() / 2, nave.getY()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void moverNave() {
        nave.actualizar();
    }

    private void moverDisparos() {
        for (int i = disparos.size() - 1; i >= 0; i--) {
            Disparo disparo = disparos.get(i);
            disparo.moverArriba();
            if (disparo.getY() < 0) {
                disparos.remove(i);
            }
        }
    }

    private void moverAsteroides() {
        for (int i = asteroides.size() - 1; i >= 0; i--) {
            Asteroide asteroide = asteroides.get(i);
            asteroide.moverAbajo();
            if (asteroide.getY() > ALTO_VENTANA) {
                asteroides.remove(i);
            }
        }
    }

    private void detectarColisiones() {
        for (int i = disparos.size() - 1; i >= 0; i--) {
            Disparo disparo = disparos.get(i);
            for (int j = asteroides.size() - 1; j >= 0; j--) {
                Asteroide asteroide = asteroides.get(j);
                if (disparo.intersecta(asteroide)) {
                    disparos.remove(i);
                    asteroides.remove(j);
                    break;
                }
            }
        }
    }

    private void generarAsteroides() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(ANCHO_VENTANA);
            int y = random.nextInt(ALTO_VENTANA / 2) - ALTO_VENTANA;
            asteroides.add(new Asteroide(x, y));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        nave.dibujar(g);

        for (Disparo disparo : disparos) {
            disparo.dibujar(g);
        }

        for (Asteroide asteroide : asteroides) {
            asteroide.dibujar(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoDeAsteroides());
    }
}

class Nave {
    private int x, y;
    private final int ancho = 40;
    private final int alto = 40;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void moverIzquierda() {
        x -= JuegoDeAsteroides.VELOCIDAD_NAVE;
        if (x < 0) {
            x = 0;
        }
    }

    public void moverDerecha() {
        x += JuegoDeAsteroides.VELOCIDAD_NAVE;
        if (x > JuegoDeAsteroides.ANCHO_VENTANA - ancho) {
            x = JuegoDeAsteroides.ANCHO_VENTANA - ancho;
        }
    }

    public void actualizar() {
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, ancho, alto);
    }
}

class Disparo {
    private int x, y;
    private final int ancho = 5;
    private final int alto = 15;

    public Disparo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moverArriba() {
        y -= JuegoDeAsteroides.VELOCIDAD_DISPARO;
    }

    public boolean intersecta(Asteroide asteroide) {
        return x + ancho >= asteroide.getX() && x <= asteroide.getX() + asteroide.getAncho()
                && y <= asteroide.getY() + asteroide.getAlto() && y + alto >= asteroide.getY();
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, ancho, alto);
    }
}

class Asteroide {
    private int x, y;
    private final int ancho = 30;
    private final int alto = 30;

    public Asteroide(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void moverAbajo() {
        y += JuegoDeAsteroides.VELOCIDAD_ASTEROIDES;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.gray);
        g.fillOval(x, y, ancho, alto);
    }
}
