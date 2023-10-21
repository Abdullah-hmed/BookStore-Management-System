package bookstore.application;

import bookstore.application.Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class cardController {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    @FXML
    private Label bookAuthor;

    
    
        
    public void SetData(Book book){
        Image image = new Image(getClass().getResourceAsStream(book.getBookSrc()));
        bookImage.setImage(image);
        
        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());
    }
}
