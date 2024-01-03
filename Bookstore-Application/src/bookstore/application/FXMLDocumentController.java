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
    public JFXButton UserButton;
    
    @FXML
    public ContextMenu contextMenu;
    
    @FXML
    private MenuItem signIn;
    
    @FXML
    private MenuItem logout;
    
    @FXML
    private MenuItem viewCart;

    
    @FXML
    private HBox cardLayoutMP;
    private List<Book> recentlyAdded;
    private List<Book> mostPopular;
    
    public Database database = new Database();
    public boolean loggedIn = false;
    
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
    
    private static FXMLDocumentController instance;

    public static FXMLDocumentController getInstance() {
        return instance;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logout.setVisible(false);
        instance = this;
        UserButton.setText("Guest");
        
        try {
            ScrollPane box = FXMLLoader.load(getClass().getResource("FrontPageCards.fxml"));
            borderPane.setCenter(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void showLogoutMenu(){
        signIn.setVisible(false);
        viewCart.setVisible(true);
        logout.setVisible(true);
    }
    
    public void logoutAccount(){
        loggedIn = false;
        logout.setVisible(false);
        viewCart.setVisible(false);
        UserButton.setText("Guest");
        signIn.setVisible(true);
        System.out.println("Logged Out!");
    }
    
    public void setUserName(String username) {
        if(UserButton != null){
            UserButton.setText(username);
        }else{
            System.out.println("Button is NULL");
        }
    }
    
    @FXML
    void HomeButton(ActionEvent event) {
        try {
            ScrollPane box = FXMLLoader.load(getClass().getResource("FrontPageCards.fxml"));
            borderPane.setCenter(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAndSetCenter(String fxmlFileName, String genre, String booktype) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            ChildrensBooksController controller = loader.getController();
            controller.initData(genre, booktype);

            borderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ArtPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Art-Phtotography", "genre");
    }

    @FXML
    void BiographyPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Biography", "genre");
    }

    @FXML
    void ChildrensBooksPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Childrens-Books", "genre");
    }

    @FXML
    void ComicsPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Crime-Thriller", "genre");
    }

    @FXML
    void FantasyPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Science-Fiction-Fantasy-Horror", "genre");
    }

    @FXML
    void HistoryPage(ActionEvent event) {
        loadAndSetCenter("ChildrensBooks.fxml", "Natural-History", "genre");
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
        printSearch();
    }
    
    void printSearch(){
        loadAndSetCenter("ChildrensBooks.fxml", searchBar.getText(), "search");
    }

    public void viewCart() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartFXML.fxml"));
        Parent root = loader.load();
        borderPane.setCenter(root);
    }
    
    public void openFAQ() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FAQ.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        FAQController controller = loader.getController();
        controller.setStage(stage);
        stage.show();
    }
    
}
