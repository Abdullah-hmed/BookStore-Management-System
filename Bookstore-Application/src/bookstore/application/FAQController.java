package bookstore.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class FAQController implements Initializable {

    @FXML
    private TextField valueField;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    Stage mainStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setStage(Stage stage) {
        mainStage = stage;
        mainStage.setOnCloseRequest(this::closeWindow);
    }

    public void sendValue() {
        try {
            String valueString = valueField.getText();
            int value = Integer.parseInt(valueString);

            if (value < 5 && value >= 1) {
                System.out.println(value);
                getResponse(value);
            } else {
                System.out.println("Invalid Question Number");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input Value. Add a valid value");
        }
    }

    public void getResponse(int value) {
        try {
            // Check if the socket is already created
            if (socket == null || socket.isClosed()) {
                // If not, create a new socket
                socket = new Socket("localhost", 8000);

                // Initialize input and output streams
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
            }

            // Send a query to the server
            writer.println(String.valueOf(value));

            // Receive the answer from the server
            String answer = reader.readLine();
            System.out.println("Server's answer: " + answer);
            showAlert("Answer", answer);
        } catch (SocketException ex) {
            System.out.println("Potential Socket Exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(WindowEvent event) {
        System.out.println("Window Shutting off");
        try {    
            writer.println("bye");
            socket.close();
            System.out.println("Socket closed.");
        } catch (IOException ex) {
            ex.printStackTrace();
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
