package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.event.Event;
import javafx.scene.control.TabPane;

public class PrimaryController {

    @FXML
    public TabPane tabpane;

    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tabAdd;

    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @FXML
    private void initialize() {
        tab1.setOnSelectionChanged(this::handleTabChange);
        tab2.setOnSelectionChanged(this::handleTabChange);
        tabAdd.setOnSelectionChanged(this::handleTabChange);
    }

    private void handleTabChange(Event event) {
        Tab selectedTab = (Tab) event.getSource();
        if (selectedTab == tab1) {
            label1.setText("Se seleccionó la Pestaña 1");
        } else if (selectedTab == tab2) {
            label2.setText("Se seleccionó la Pestaña 2");
        } else if (selectedTab == tabAdd) {
            Tab newTab = new Tab("New Tab", new Label("Esta es una pestaña nueva"));
            tabpane.getTabs().add(tabpane.getTabs().size()-1, newTab);
            tabpane.getSelectionModel().select(tabpane.getTabs().size()-2);
        }
    }
}
