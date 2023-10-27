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
    
    
    private final String[] colors = {"ffc9c9","fbf3c5","c2fdbc","b6e4fb","e1c6fd"};
    
    public void SetData(Book book){
        Image image = new Image(new ByteArrayInputStream(book.getBookSrc()));
        bookImage.setImage(image);
        
        bookName.setText(book.getBookName());
        bookAuthor.setText("By "+book.getBookAuthor());
        bookPrice.setText("Rs. "+String.valueOf((book.getPrice())));
    }
    
    public void GetData(){
        System.out.println("Name: "+bookName.getText()+"\nAuthor: "+bookAuthor.getText()+"\nPrice: "+bookPrice.getText());
    }
}
