package org.example;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class FileTreeItem extends TreeItem<String> {

    private File file;

    public FileTreeItem(File file) {
        super(file.getName());
        this.file = file;
        if (file.isDirectory()) {
            this.setGraphic(createGraphic("/folder.png"));
        } else {
            this.setGraphic(createGraphic("/file.png"));
        }
    }

    private ImageView createGraphic(String filename){
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(filename)));
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public File getFile() {
        return file;
    }
}
