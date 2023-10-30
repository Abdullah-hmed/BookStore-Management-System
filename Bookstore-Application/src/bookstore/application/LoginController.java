/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

/**
 *
 * @author Alli
 */

import bookstore.application.user.UserAuthentication;
import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
    
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author Alli
 */

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton loginButton;

    @FXML
    void Login(ActionEvent event) {
        
    }

    private UserAuthentication userAuth;

    public LoginController() {
        userAuth = new UserAuthentication(); // Create an instance of UserAuthentication
    }

    @FXML
    public void loginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userAuth.login(username, password)) {
            // Login successful
            showAlert("Login Successful", "Welcome, " + username + "!");
        } else {
            // Login failed
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
    
    



 




