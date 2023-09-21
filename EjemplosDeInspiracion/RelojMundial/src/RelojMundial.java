import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class RelojMundial extends JFrame implements ActionListener {
    private ArrayList<Ciudad> ciudades;
    private JComboBox<String> selectorCiudades;
    private JLabel etiquetaHora;

    public RelojMundial() {
        // Configuración de la ventana
        setTitle("Reloj Mundial");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar lista de ciudades
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad("Nueva York", "America/New_York"));
        ciudades.add(new Ciudad("Londres", "Europe/London"));
        ciudades.add(new Ciudad("Tokio", "Asia/Tokyo"));

        // Crear selector de ciudades
        selectorCiudades = new JComboBox<>();
        for (Ciudad ciudad : ciudades) {
            selectorCiudades.addItem(ciudad.getNombre());
        }
        selectorCiudades.addActionListener(this);

        // Crear etiqueta para mostrar la hora
        etiquetaHora = new JLabel();
        etiquetaHora.setFont(new Font("Arial", Font.PLAIN, 24));

        // Crear panel para organizar componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(selectorCiudades);
        panel.add(etiquetaHora);

        // Agregar panel a la ventana
        add(panel);

        // Iniciar temporizador para actualizar la hora cada segundo
        Timer timer = new Timer(1000, this);
        timer.start();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectorCiudades) {
            actualizarHora();
        } else {
            // Actualizar la hora cada segundo
            actualizarHora();
        }
    }

    private void actualizarHora() {
        int indiceCiudadSeleccionada = selectorCiudades.getSelectedIndex();
        if (indiceCiudadSeleccionada >= 0 && indiceCiudadSeleccionada < ciudades.size()) {
            Ciudad ciudadSeleccionada = ciudades.get(indiceCiudadSeleccionada);
            TimeZone zonaHoraria = TimeZone.getTimeZone(ciudadSeleccionada.getZonaHoraria());
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            formatoHora.setTimeZone(zonaHoraria);
            String hora = formatoHora.format(new Date());
            etiquetaHora.setText(ciudadSeleccionada.getNombre() + ": " + hora);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RelojMundial());
    }
}

class Ciudad {
    private String nombre;
    private String zonaHoraria;

    public Ciudad(String nombre, String zonaHoraria) {
        this.nombre = nombre;
        this.zonaHoraria = zonaHoraria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }
}
