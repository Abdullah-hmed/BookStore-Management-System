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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author SC
 */
public class FXMLDocumentController implements Initializable {
        
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private JFXButton ArtButton;

    @FXML
    private JFXButton BiographyButton;

    @FXML
    private JFXButton ScifiButton;

    @FXML
    private JFXButton FantasyButton;

    @FXML
    private JFXButton NonfictionButton;

    @FXML
    private JFXButton HistoryButton;

    @FXML
    private JFXButton ThrillerButton;

    @FXML
    private JFXButton RomanceButton;

    @FXML
    private JFXButton ComicsButton;

    @FXML
    private JFXButton ChildrenBooksButton;
    
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
    
    public Database database = new Database();
    
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
            VBox box = FXMLLoader.load(getClass().getResource("FrontPageCards.fxml"));
            borderPane.setCenter(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void HomeButton(ActionEvent event) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("FrontPageCards.fxml"));
            borderPane.setCenter(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAndSetCenter(String fxmlFileName, String genre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            ChildrensBooksController controller = loader.getController();
            controller.initData(genre);

            borderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ArtPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Art-Phtotography");
    }

    @FXML
    void BiographyPage(ActionEvent event) {

    }

    @FXML
    void ChildrensBooksPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Childrens-Books");
    }

    @FXML
    void ComicsPage(ActionEvent event) {
        
    }

    @FXML
    void FantasyPage(ActionEvent event) {

    }

    @FXML
    void HistoryPage(ActionEvent event) {

    }

    @FXML
    void NonFictionPage(ActionEvent event) {

    }

    @FXML
    void RomancePage(ActionEvent event) {

    }

    @FXML
    void ScifiPage(ActionEvent event) {

    }

    @FXML
    void ThrillerPage(ActionEvent event) {

    }
    
     @FXML
    void searchButton(ActionEvent event) {
        List<Book> output;
        String search = searchBar.getText();
        output = database.searchResult(search);
        for(int i=0;i<output.size();i++){
            output.get(i).GetData();
        }
    }
}
