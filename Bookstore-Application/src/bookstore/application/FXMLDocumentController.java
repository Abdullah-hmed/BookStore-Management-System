/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import bookstore.application.Model.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author SC
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private HBox cardLayout;
    private List<Book> recentlyAdded;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for(int i=0;i<recentlyAdded.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(recentlyAdded.get(i));
                cardLayout.getChildren().add(cardBox);
            }   
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }    
    public List<Book> recentlyAdded (){
        List<Book> bookList = new ArrayList<>();
        Book book = new Book("A Street Cat Named Bob","James Bowen","BookCovers/0000001.jpg");
        bookList.add(book);
        book = new Book("Animal Farm","George Orwell","0000003.jpg");
        bookList.add(book);
        book = new Book("Into Thin Air","Jon Krakauer","0000005.jpg");
        bookList.add(book);
        book = new Book("The Dry","Jane Harper","0000007.jpg");
        bookList.add(book);
        book = new Book("The Hidden Life of Trees","Peter Wohlleben","0000009.jpg");
        bookList.add(book);
        book = new Book("World War Z","Max Brooks","0000187.jpg");
        bookList.add(book);
        return bookList;
    }

    
}
