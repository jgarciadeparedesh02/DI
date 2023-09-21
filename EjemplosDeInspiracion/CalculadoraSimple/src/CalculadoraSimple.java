import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSimple extends JFrame implements ActionListener {
    // Componentes
    private final JTextField pantalla;
    private double num1, num2, resultado;
    private int operacion;

    public CalculadoraSimple() {
        // Configuración de la ventana
        setTitle("Calculadora Simple");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        // Inicialización de variables
        num1 = 0;
        num2 = 0;
        resultado = 0;
        operacion = 0;

        // Crear el campo de texto
        pantalla = new JTextField();
        pantalla.setFont(new Font("Arial", Font.PLAIN, 24));
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);

        // Crear el panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4));

        // Crear los botones de dígitos y operadores
        String[] etiquetas = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"};

        for (String etiqueta : etiquetas) {
            JButton boton = new JButton(etiqueta);
            boton.setFont(new Font("Arial", Font.PLAIN, 18));
            boton.addActionListener(this);
            panelBotones.add(boton);
        }

        add(panelBotones);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                pantalla.setText(pantalla.getText() + comando);
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                num1 = Double.parseDouble(pantalla.getText());
                operacion = comando.charAt(0);
                pantalla.setText("");
                break;

            case "=":
                num2 = Double.parseDouble(pantalla.getText());
                switch (operacion) {
                    case '+':
                        resultado = num1 + num2;
                        break;
                    case '-':
                        resultado = num1 - num2;
                        break;
                    case '*':
                        resultado = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0)
                            resultado = num1 / num2;
                        else
                            pantalla.setText("Error");
                        break;
                }
                pantalla.setText(String.valueOf(resultado));
                break;

            case "C":
                pantalla.setText("");
                num1 = 0;
                num2 = 0;
                resultado = 0;
                operacion = 0;
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSimple());
    }
}
