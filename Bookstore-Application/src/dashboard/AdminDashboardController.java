package dashboard;

import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class AdminDashboardController implements Initializable{

    @FXML
    private TableView<?> bookTable;

    @FXML
    private TableColumn<String, Book> bookColumn;

    @FXML
    private TableColumn<String, Book> authorColumn;

    @FXML
    private TableColumn<String, Book> genreColumn;

    @FXML
    private TableColumn<Float, Book> priceColumn;

    
    public void chooseBookImage(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
    }
    
    public void loadBooks(){
        Database database = new Database();
        List<Book> Booklist = database.Booklist();
        //continue on from here.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    
}
