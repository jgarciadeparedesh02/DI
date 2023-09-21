import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojDigital extends JFrame {
    private JLabel etiquetaHora;

    public RelojDigital() {
        // Configuración de la ventana
        setTitle("Reloj Digital");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear etiqueta para mostrar la hora
        etiquetaHora = new JLabel();
        etiquetaHora.setFont(new Font("Arial", Font.PLAIN, 36));
        etiquetaHora.setHorizontalAlignment(JLabel.CENTER);
        actualizarHora(); // Actualizar la hora inicial

        // Agregar etiqueta a la ventana
        add(etiquetaHora);

        // Crear temporizador para actualizar la hora cada segundo
        Timer temporizador = new Timer(1000, e -> actualizarHora());
        temporizador.start();

        setVisible(true);
    }

    private void actualizarHora() {
        // Obtener la hora actual
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formatoHora.format(new Date());

        // Actualizar la etiqueta con la hora actual
        etiquetaHora.setText(horaActual);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RelojDigital());
    }
}
