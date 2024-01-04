/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import Cart.Cart;
import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import static bookstore.application.Model.Database.getDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class CartFXMLController implements Initializable {

    @FXML
    private TableView<Cart> cartTable;

    @FXML
    private TableColumn<Cart, String> bookname;

    @FXML
    private TableColumn<Cart, String> bookauthor;

    @FXML
    private TableColumn<Cart, String> bookgenre;

    @FXML
    private TableColumn<Cart, Integer> bookamount;

    @FXML
    private TableColumn<Cart, Float> bookprice;
    
    @FXML
    private TableColumn<Cart, Float> totalbookprice;
    
    Database database = getDatabase();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookname.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        bookauthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
        bookgenre.setCellValueFactory(new PropertyValueFactory<>("BookGenre"));
        bookprice.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        bookamount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        totalbookprice.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
        loadCart();
    }    
    
    
    public void loadCart(){
        List<Cart> cartList = database.retrieveCart();
        ObservableList<Cart> observableCartlist = FXCollections.observableArrayList(cartList);
        cartTable.setItems(observableCartlist);
    }
    
        
    public void removeFromCart(){
        int selectedID = cartTable.getSelectionModel().getSelectedIndex();
        
        Cart BookToBeRemoved = cartTable.getItems().get(selectedID);
        int BookID = BookToBeRemoved.getBookID();
        System.out.println(BookID);
        boolean bookRemoved = database.removeFromCart(BookID);
        
        if(bookRemoved){
            cartTable.getItems().remove(selectedID);
        }
    }
    
    
    public void checkout() throws IOException{
        database.createOrder();
        loadCart();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("billFXML.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Bill");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
