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

    import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
    
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    private Button loginButton;

    @FXML
    public void loginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        
        if (isValidUser(username, password)) {

            System.out.println("Login successful");
        } else {
            
            System.out.println("Login failed");
        }
    }

    private boolean isValidUser(String username, String password) {
      
        return "user".equals(username) && "password".equals(password);
    }
    
    



 
}




