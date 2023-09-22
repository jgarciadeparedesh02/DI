import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface {
    private JPanel panel;
    private JList list;
    private JTextField textField;
    private JButton agregarButton;
    private JButton eliminarButton;

    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public MainInterface() {
        list.setModel(listModel);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newItem = textField.getText().trim();
                if (!newItem.isEmpty()) {
                    listModel.addElement(newItem);
                    textField.setText("");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
