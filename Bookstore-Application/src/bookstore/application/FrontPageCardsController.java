package bookstore.application;

import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class FrontPageCardsController implements Initializable {

    @FXML
    private HBox cardLayoutRA;

    @FXML
    private HBox cardLayoutMP;

    @FXML
    private ProgressBar progressBarRA;

    @FXML
    private ProgressBar progressBarMP;
    
    private List<Book> recentlyAdded;
    private List<Book> mostPopular;

    FXMLDocumentController controller = new FXMLDocumentController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AsyncLoading();
    }

    private void AsyncLoading() {
        Task<List<Book>> taskRA = new Task<List<Book>>() {
            @Override
            protected List<Book> call() throws Exception {
                return controller.database.recentlyAdded();
            }
        };

        Task<List<Book>> taskMP = new Task<List<Book>>() {
            @Override
            protected List<Book> call() throws Exception {
                return controller.database.mostPopular();
            }
        };

        taskRA.setOnSucceeded(event -> {
            recentlyAdded = taskRA.getValue();
            UserInteface(cardLayoutRA, recentlyAdded);
        });

        taskMP.setOnSucceeded(event -> {
            mostPopular = taskMP.getValue();
            UserInteface(cardLayoutMP, mostPopular);
        });

        new Thread(taskRA).start();
        new Thread(taskMP).start();
    }

    private void UserInteface(HBox cardLayout, List<Book> bookList) {
        try {
            for (int i = 0; i < bookList.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(bookList.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

