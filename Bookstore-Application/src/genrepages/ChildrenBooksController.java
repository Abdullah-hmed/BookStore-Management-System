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
import bookstore.application.CardVertController;
import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import static bookstore.application.Model.Database.getDatabase;
import bookstore.application.cardController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ChildrenBooksController implements Initializable {
    

    @FXML
    private VBox Box;
  
    public ListView<String> fictionListView;
    private List<Book> ByGenre;
    public String genre="Childrens-Books";
    Database database = getDatabase();


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
            for(int i=0;i<ByGenre.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                VBox cardBox = FXMLLoader.load(getClass().getResource("CardVert.fxml"));
                CardVertController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(ByGenre.get(i));
                Box.getChildren().add(cardBox);
            }   
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // You can add methods and event handlers here
}

