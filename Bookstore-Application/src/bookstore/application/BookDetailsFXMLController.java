/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import bookstore.application.Model.Database;
import static bookstore.application.Model.Database.getDatabase;
import bookstore.application.user.User;
import com.jfoenix.controls.JFXSlider;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class BookDetailsFXMLController implements Initializable {
    
    String Name;
    String Author;
    String Price;
    int bookID;
    
    @FXML
    private Label nameLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label priceLabel;
    
    @FXML
    private ImageView bookImage;
    
    @FXML
    private Label bookAddedLabel;
        
    @FXML
    private TextField amount;
    
    Database database = getDatabase();
    int userID = database.getUserID();
    
    @FXML
    void addToCart(MouseEvent event) throws InterruptedException {
        FXMLDocumentController controller = FXMLDocumentController.getInstance();
        int bookAmount = 1;
        if(controller.loggedIn){
            System.out.println(userID+", "+bookID);
            bookAmount = Integer.parseInt(amount.getText().trim());
            if(amount.getText().isEmpty()){
                bookAmount = 1;
            }
            database.addToCart(bookID, bookAmount);
            bookAddedLabel.setVisible(true);
        }else{
            bookAddedLabel.setVisible(true);
            bookAddedLabel.setText("Please Log into your account!");
            bookAddedLabel.setTextFill(Color.color(1, 0, 0));
        }
    }
    
    public void setData(int bookID, String name, String author, String price, Image image){
        this.bookID = bookID;
        nameLabel.setText(name);
        authorLabel.setText(author);
        priceLabel.setText(price);
        bookImage.setImage(image);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookAddedLabel.setVisible(false);
    }
    
}
