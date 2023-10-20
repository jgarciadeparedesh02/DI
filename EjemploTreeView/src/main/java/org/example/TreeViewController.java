package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewController {

    @FXML
    private TreeView<String> treeView;

    @FXML
    public void initialize() {
        // Configura un oyente para manejar los clics en elementos del TreeView
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedValue = newValue.getValue();
                // Si es un archivo, muestra una alerta
                if (selectedValue.startsWith("File")) {
                    showAlert("Archivo seleccionado", "Has seleccionado el archivo: " + selectedValue);
                }
            }
        });
    }

    @FXML
    private void deleteFile(ActionEvent event) {
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String selectedValue = selectedItem.getValue();
            if (selectedValue.startsWith("File")) {
                TreeItem<String> parent = selectedItem.getParent();
                parent.getChildren().remove(selectedItem);
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
