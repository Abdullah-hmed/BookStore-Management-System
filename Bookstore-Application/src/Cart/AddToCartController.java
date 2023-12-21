/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

/**
 *
 * @author Laiba Asif
 */

import bookstore.application.Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AddToCartController {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;

    @FXML
    private Label bookPrice;

    @FXML
    private Button addToCartButton;

    private Book book;
    private Cart cart;

    public void setBook(Book book, Cart cart) {
        this.book = book;
        this.cart = cart;

        // Set the book details in the UI components
     //   bookImage.setImage(new Image(book.getBookSrc()));
        bookTitle.setText(book.getBookName());
        bookPrice.setText("$" + book.getPrice());
    }

    @FXML
    private void handleAddToCartButtonAction() {
        // Add the selected book to the cart
        

        // Show a confirmation message to the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cart Updated");
        alert.setHeaderText(null);
        alert.setContentText("The book has been added to your cart.");
        alert.showAndWait();
    }
}
