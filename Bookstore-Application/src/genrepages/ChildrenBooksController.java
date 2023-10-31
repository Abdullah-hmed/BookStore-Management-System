/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genrepages;

/**
 *
 * @author Alli
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildrenBooksController implements Initializable {

    @FXML
    private BorderPane rootPane;

    @FXML
    public VBox centerPane;

    @FXML
     Label headerLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // You can initialize your controller here if needed
    }

    // You can add methods and event handlers here

    public BorderPane getRootPane() {
        return rootPane;
    }
}

