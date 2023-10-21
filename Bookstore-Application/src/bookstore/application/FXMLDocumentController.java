/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import bookstore.application.Model.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author SC
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private HBox cardLayout;
    private List<Book> recentlyAdded;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    

    
}
