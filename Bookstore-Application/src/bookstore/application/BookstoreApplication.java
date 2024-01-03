package bookstore.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BookstoreApplication extends Application {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    double screenWidth = bounds.getWidth();
    double screenHeight = bounds.getHeight();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //stage.setMinHeight(890);
        //stage.setMinWidth(1315);
        stage.setWidth(screenWidth * 0.8);  // Adjust these values as needed
        stage.setHeight(screenHeight * 0.8);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
