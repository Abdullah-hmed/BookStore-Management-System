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

import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import bookstore.application.cardController;
import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;



public class ThrillerController implements Initializable {
         
    @FXML
    private TextField searchBar;
    
    @FXML
    private ScrollPane scrollPaneRecent;
    
    @FXML
    private HBox cardLayoutRA;

    @FXML
    private JFXButton UserButton;
    
    @FXML
    private MenuItem loginOption;

    @FXML
    private MenuItem signinOption;
    
    @FXML
    private HBox cardLayoutMP;
 
    private List<Book> ByGenre;
    public String genre="Thriller";
    Database database = new Database();
    public ListView<String> fictionListView;
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
            database.connect(); //database connection established
        } catch (SQLException ex) {
            System.out.println("Database not connected successfully");
            ex.printStackTrace();
        }
     
       
        ByGenre = new ArrayList<>(database.ByGenre(genre));

    try {
        for (int i = 0; i < ByGenre.size(); i++) {
            Book book = ByGenre.get(i);
            if (book.getGenre().equals(genre)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                cardController cardController = fxmlLoader.getController();
                cardController.SetData(book);

                cardLayoutMP.getChildren().add(cardBox);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }  
        
        
    }  
    }

