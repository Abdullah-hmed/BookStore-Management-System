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

import bookstore.application.user.User;
import bookstore.application.user.UserAuthentication;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
    
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Alli
 */

public class LoginController {

    @FXML
    private AnchorPane Window;
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton loginButton;
    
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField usernameReg;

    @FXML
    private PasswordField passwordReg;

    @FXML
    private JFXButton signupButton;

    @FXML
    private Pane SigninPane;
    
    @FXML
    private Pane loginPane;
    
    @FXML
    private TextField Address;

    @FXML
    private TextField Phone;
    
    
    
    Parent root;
    
    @FXML
    void LoginUser(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (userAuth.login(username, password)) {
            showAlert("Login Successful", "Welcome, " + username + "!",true);
            if(username.equals("admin") && password.equals("admin")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard/AdminDashboard.fxml"));
                Parent dashboard = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(dashboard));
                stage.setTitle("Admin Dashboard");
                stage.initStyle(StageStyle.UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.show();
            }
        } else {
            showAlert("Login Failed", "Invalid username or password.",false);
        }
    }
    
    @FXML
    void SignUp(ActionEvent event) {
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String Email = email.getText();
        String username = usernameReg.getText();
        String password = passwordReg.getText();
        String address = Address.getText();
        String phone = Phone.getText();
        User user = new User(username, password);
        user.setEmail(Email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setPhone(phone);
        userAuth.register(user);
        
    }
    
    @FXML
    void SignupForm(ActionEvent event) {
        SigninPane.toFront();
    }

    @FXML
    void loginForm(ActionEvent event) {
        loginPane.toFront();
    }

    private UserAuthentication userAuth;

    public LoginController() {
        userAuth = new UserAuthentication(); // Create an instance of UserAuthentication
    }
    
    Stage stage;
    private void showAlert(String title, String message, boolean loggedIn) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if(alert.showAndWait().get() == ButtonType.OK && loggedIn){
            stage = (Stage) Window.getScene().getWindow();
            stage.close();
        }
    }
}
    
    



 




