package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PrimaryController {

    @FXML
    private TreeView<String> fileTreeView;

    @FXML
    private TextArea fileContentTextArea;

    @FXML
    public void initialize() {
        File rootDirectory = new File("G:\\Mi unidad\\A_Educación_PasarACorreoEducarex\\2223 - IES Albarregas - Funcionario Prácticas\\DAM\\DI - 0488\\Repositorio\\DI\\SolucionTareas\\TareaTreeViewSistemaDeArchivos");
        FileTreeItem rootItem = createFileTree(rootDirectory);
        fileTreeView.setRoot(rootItem);

        fileTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                File selectedFile = ((FileTreeItem) newValue).getFile();
                if (selectedFile.isFile()) {
                    try {
                        String content = new String(Files.readAllBytes(selectedFile.toPath()));
                        fileContentTextArea.setText(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private FileTreeItem createFileTree(File directory) {
        FileTreeItem item = new FileTreeItem(directory);
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                item.getChildren().add(createFileTree(file));
            } else {
                item.getChildren().add(new FileTreeItem(file));
            }
        }
        return item;
    }
}
