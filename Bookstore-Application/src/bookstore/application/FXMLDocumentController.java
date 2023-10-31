/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author SC
 */
public class FXMLDocumentController implements Initializable {
        
  
    @FXML
    private TextField searchBar;
    
    @FXML
    private ScrollPane scrollPaneRecent;
    
    @FXML
    private HBox cardLayoutRA;

    @FXML
    private JFXButton UserButton;
    
    @FXML
    public ContextMenu contextMenu;
    
    @FXML
    private HBox cardLayoutMP;
    private List<Book> recentlyAdded;
    private List<Book> mostPopular;
    
    Database database = new Database();
    
    @FXML
    void loginUser(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            database.connect(); //database connection established
        } catch (SQLException ex) {
            System.out.println("Database not connected successfully");
            ex.printStackTrace();
        }
        
        recentlyAdded = new ArrayList<>(database.recentlyAdded());
        mostPopular = new ArrayList<>(database.mostPopular());
        try {
            for(int i=0;i<recentlyAdded.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(recentlyAdded.get(i));
                cardLayoutRA.getChildren().add(cardBox);
            }   
            for(int i=0;i<mostPopular.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(mostPopular.get(i));
                cardLayoutMP.getChildren().add(cardBox);
            }   
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
}
