import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora Simple");
        frame.getContentPane().add(new FormularioCalculadora().getPanel());
        frame.setSize(400, 800);

        frame.setVisible(true);
    }
}