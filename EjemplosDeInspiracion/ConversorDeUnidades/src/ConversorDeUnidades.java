import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorDeUnidades extends JFrame implements ActionListener {
    private JComboBox<String> unidadesDeEntrada, unidadesDeSalida;
    private JTextField campoDeEntrada, campoDeSalida;

    public ConversorDeUnidades() {
        // Configuración de la ventana
        setTitle("Conversor de Unidades");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear etiquetas
        JLabel etiquetaEntrada = new JLabel("Valor:");
        JLabel etiquetaSalida = new JLabel("Resultado:");

        // Crear campos de texto
        campoDeEntrada = new JTextField(10);
        campoDeSalida = new JTextField(10);
        campoDeSalida.setEditable(false);

        // Crear listas desplegables (JComboBox) para unidades de entrada y salida
        unidadesDeEntrada = new JComboBox<>(new String[]{"Metros", "Pies", "Pulgadas"});
        unidadesDeSalida = new JComboBox<>(new String[]{"Metros", "Pies", "Pulgadas"});

        // Crear botón de conversión
        JButton botonConvertir = new JButton("Convertir");
        botonConvertir.addActionListener(this);

        // Crear paneles para organizar componentes
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2));
        panelSuperior.add(etiquetaEntrada);
        panelSuperior.add(campoDeEntrada);
        panelSuperior.add(etiquetaSalida);
        panelSuperior.add(campoDeSalida);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        panelInferior.add(unidadesDeEntrada);
        panelInferior.add(botonConvertir);
        panelInferior.add(unidadesDeSalida);

        // Agregar componentes a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener valores de entrada y unidades seleccionadas
        double valorEntrada = Double.parseDouble(campoDeEntrada.getText());
        String unidadEntrada = unidadesDeEntrada.getSelectedItem().toString();
        String unidadSalida = unidadesDeSalida.getSelectedItem().toString();

        // Realizar la conversión de unidades
        double valorSalida = convertirUnidades(valorEntrada, unidadEntrada, unidadSalida);

        // Mostrar el resultado en el campo de salida
        DecimalFormat formato = new DecimalFormat("#.##");
        campoDeSalida.setText(formato.format(valorSalida));
    }

    private double convertirUnidades(double valor, String unidadEntrada, String unidadSalida) {
        // Factores de conversión
        double metroA_Pies = 3.28084;
        double metroA_Pulgadas = 39.3701;

        // Realizar la conversión a metros
        if (unidadEntrada.equals("Pies")) {
            valor /= metroA_Pies;
        } else if (unidadEntrada.equals("Pulgadas")) {
            valor /= metroA_Pulgadas;
        }

        // Realizar la conversión desde metros
        if (unidadSalida.equals("Pies")) {
            valor *= metroA_Pies;
        } else if (unidadSalida.equals("Pulgadas")) {
            valor *= metroA_Pulgadas;
        }

        return valor;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConversorDeUnidades());
    }
}
