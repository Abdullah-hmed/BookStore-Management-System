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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author SC
 */
public class FXMLDocumentController implements Initializable {
    
    Random rnd = new Random();
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private ScrollPane scrollPaneRecent;
    
    @FXML
    private HBox cardLayoutRA;

    @FXML
    private HBox cardLayoutMP;
    private List<Book> recentlyAdded;
    private List<Book> mostPopular;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        recentlyAdded = new ArrayList<>(recentlyAdded());
        mostPopular = new ArrayList<>(mostPopular());
        try {
            for(int i=0;i<recentlyAdded.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(recentlyAdded.get(i));
                cardLayoutRA.getChildren().add(cardBox);
            }   
            for(int i=0;i<mostPopular.size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlloader.load();
                cardController cardcontroller = fxmlloader.getController();
                cardcontroller.SetData(mostPopular.get(i));
                cardLayoutMP.getChildren().add(cardBox);
            }   
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
    }    
    public List<Book> recentlyAdded (){ //ArrayList containing all the books to be added into the Recently Added ScrollPane
        List<Book> bookList = new ArrayList<>();
        Book book = new Book("A Street Cat Named Bob","James Bowen","BookCover/0000001.jpg", 400);
        bookList.add(book);
        book = new Book("Animal Farm","George Orwell","BookCover/0000003.jpg", 800);
        bookList.add(book);
        book = new Book("Into Thin Air","Jon Krakauer","BookCover/0000005.jpg", 300);
        bookList.add(book);
        book = new Book("The Dry","Jane Harper","BookCover/0000007.jpg", 600);
        bookList.add(book);
        book = new Book("The Hidden Life of Trees","Peter Wohlleben","BookCover/0000009.jpg", 700);
        bookList.add(book);
        book = new Book("World War Z","Max Brooks","BookCover/0000187.jpg", 1000);
        bookList.add(book);
        return bookList;
    }
    
        
    public List<Book> mostPopular (){ //ArrayList containing all the books to be added into the Most Popular ScrollPane
        List<Book> bookList = new ArrayList<>();
        Book book = new Book("World War Z","Max Brooks","BookCover/0000187.jpg", 1000);
        bookList.add(book);
        book = new Book("Animal Farm","George Orwell","BookCover/0000003.jpg", 800);
        bookList.add(book);
        book = new Book("A Street Cat Named Bob","James Bowen","BookCover/0000001.jpg", 400);
        bookList.add(book);
        book = new Book("Into Thin Air","Jon Krakauer","BookCover/0000005.jpg", 300);
        bookList.add(book);
        book = new Book("The Dry","Jane Harper","BookCover/0000007.jpg", 600);
        bookList.add(book);
        book = new Book("The Hidden Life of Trees","Peter Wohlleben","BookCover/0000009.jpg", 700);
        bookList.add(book);
        
        return bookList;
    }

    
}
