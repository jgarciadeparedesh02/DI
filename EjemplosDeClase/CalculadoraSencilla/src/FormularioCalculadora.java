import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCalculadora {
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a3Button;
    private JButton a2Button;
    private JPanel panel;
    private JLabel label;
    private JButton substractButton;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton equalsButton;
    private JButton a0Button;

    int num1 = -1;
    int num2 = -1;
    String expr;

    public FormularioCalculadora() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(num1 == -1) {
                        num1 = Integer.parseInt(e.getActionCommand());
                    } else {
                        num2 = Integer.parseInt(e.getActionCommand());
                    }
                } catch (NumberFormatException exception) {
                    if(e.getActionCommand() != "="){
                        expr = e.getActionCommand();
                    } else {
                        switch (expr) {
                            case "+":
                                label.setText(String.valueOf(num1 + num2));
                                break;
                            case "-":
                                label.setText(String.valueOf(num1 - num2));
                                break;
                            case "*":
                                label.setText(String.valueOf(num1 * num2));
                                break;
                        }
                        num1 = -1;
                        num2 = -1;
                    }
                }
            }
        };

        JButton[] buttonArray = new JButton[]{a1Button, a2Button, a3Button, a4Button, a5Button, a6Button, a7Button, a8Button, a9Button, equalsButton, addButton, substractButton, multiplyButton};
        for (int i = 0; i < buttonArray.length; i++)
            buttonArray[i].addActionListener(listener);

    }

    public JPanel getPanel() {
        return panel;
    }
}
