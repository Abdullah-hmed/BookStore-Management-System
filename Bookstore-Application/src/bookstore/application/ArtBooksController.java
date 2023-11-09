/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import bookstore.application.CardVertController;
import bookstore.application.CardVertController;
import bookstore.application.CardVertController;
import bookstore.application.CardVertController;
import bookstore.application.FXMLDocumentController;
import bookstore.application.FXMLDocumentController;
import bookstore.application.FXMLDocumentController;
import bookstore.application.FXMLDocumentController;
import bookstore.application.Model.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class ArtBooksController implements Initializable {

    @FXML
    private Label genreLabel;

    @FXML
    private TilePane bookTiles;
    private List<Book> ByGenre;
    public String genre="Art-Phtotography";
    
    
    
    FXMLDocumentController controller = new FXMLDocumentController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genreLabel.setText(genre);
        ByGenre = new ArrayList<>(controller.database.ByGenre(genre));
        
        for(int i=0;i<ByGenre.size();i++){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CardVert.fxml"));
                VBox cardBox = loader.load();
                CardVertController cardController = loader.getController();
                cardController.SetData(ByGenre.get(i));
                bookTiles.getChildren().add(cardBox);
            } catch (IOException ex) {
                Logger.getLogger(ChildrensBooksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}
