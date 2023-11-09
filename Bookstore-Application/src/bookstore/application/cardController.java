package bookstore.application;

import bookstore.application.Model.Book;
import java.io.ByteArrayInputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class cardController {
    
    @FXML
    private HBox card;
    
    @FXML
    private Label bookGenre;
    
    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    @FXML
    private Label bookAuthor;

    @FXML
    private Label bookPrice;
    
    @FXML
    void DisplayBook(MouseEvent event) {
        GetData();
    }
    
    public void SetData(Book book){
        Image image = new Image(new ByteArrayInputStream(book.getBookSrc()));
        bookImage.setImage(image);
        bookGenre.setText(book.getGenre());
        bookName.setText(book.getBookName());
        bookAuthor.setText("By "+book.getBookAuthor());
        bookPrice.setText("Rs. "+String.valueOf((book.getPrice())));
    }
    
    public void GetData(){
        System.out.println("Name: "+bookName.getText()+"\nAuthor: "+bookAuthor.getText()+"\nGenre: "+bookGenre.getText()+"\nPrice: "+bookPrice.getText());
    }
}
