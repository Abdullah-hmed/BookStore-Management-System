/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

/**
 *
 * @author Alli
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AdminDashboardLoader extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Bookstore Management System - Admin Dashboard");
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}

