package bookstore.application;

import bookstore.application.Model.Book;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    int bookID;
        
    
    
    @FXML
    void DisplayBook(MouseEvent event) throws IOException {
        GetData();
        OpenBookPage();
    }
    
    public void SetData(Book book){
        Image image = new Image(new ByteArrayInputStream(book.getBookSrc()));
        bookImage.setImage(image);
        bookGenre.setText(book.getGenre());
        bookName.setText(book.getBookName());
        bookAuthor.setText("By "+book.getBookAuthor());
        bookPrice.setText("Rs. "+String.valueOf(book.getPrice()));
        bookID = book.getBookID();
    }
    public void GetData(){
        System.out.println("ID:"+bookID+"Name: "+bookName.getText()+"\nAuthor: "+bookAuthor.getText()+"\nGenre: "+bookGenre.getText()+"\nPrice: "+bookPrice.getText());
    }
    
    public void OpenBookPage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookDetailsFXML.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Book Data");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        BookDetailsFXMLController controller = loader.getController();
        controller.setData(bookID, bookName.getText(), bookAuthor.getText(), bookPrice.getText(), bookImage.getImage());
    }
}
