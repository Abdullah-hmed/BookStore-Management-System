/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feedback;

/**
 *
 * @author Laiba Asif
 */


import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.Initializable;


public class FeedbackController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField feedbackTextField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleSubmitButtonAction() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String feedback = feedbackTextField.getText();

        // Create a Feedback object and set the values
        Feedback userFeedback = new Feedback();
        userFeedback.setName(name);
        userFeedback.setPhone(phone);
        userFeedback.setEmail(email);
        userFeedback.setFeedback(feedback);

        // Process the user feedback (e.g., save to a database)
        processFeedback(userFeedback);

        // Clear the text fields
        nameTextField.clear();
        phoneTextField.clear();
        emailTextField.clear();
        feedbackTextField.clear();
    }

    private void processFeedback(Feedback feedback) {
        // Implement the logic to save feedback to a database or perform other actions.
        // Set up a database connection and save the feedback data.
        // You can also work with the `stage` here if needed.
    }
}
