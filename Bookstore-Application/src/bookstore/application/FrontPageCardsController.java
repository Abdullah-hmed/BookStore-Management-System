package bookstore.application;

import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class FrontPageCardsController implements Initializable {

    @FXML
    private ScrollPane scrollPaneRecent;

    @FXML
    private HBox cardLayoutRA;

    @FXML
    private HBox cardLayoutMP;
    private List<Book> recentlyAdded;
    private List<Book> mostPopular;
    FXMLDocumentController controller = new FXMLDocumentController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        recentlyAdded = new ArrayList<>(controller.database.recentlyAdded());
        mostPopular = new ArrayList<>(controller.database.mostPopular());
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
