import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("ListaDeLaCompra");
        frame.getContentPane().add(new MainInterface().getPanel());

        frame.setVisible(true);
    }
}
