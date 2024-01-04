/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.application;

import Cart.Cart;
import bookstore.application.Model.Database;
import static bookstore.application.Model.Database.getDatabase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author SC
 */
public class BillFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TableView<Cart> billTable;

    @FXML
    private TableColumn<Cart, Integer> quantity;

    @FXML
    private TableColumn<Cart, String> bookName;

    @FXML
    private TableColumn<Cart, Float> bookPrice;

    @FXML
    private Label totalprice;
    
    Database database = getDatabase();
    int totalCost = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Cart> cartList = database.createBill();
        quantity.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        ObservableList<Cart> observableBilllist = FXCollections.observableArrayList(cartList);
        billTable.setItems(observableBilllist);
        
        for (Cart cartItem : cartList) {
            totalCost += cartItem.getBookPrice() * cartItem.getAmount();
        }
        
        totalprice.setText(""+totalCost);
    }    
    
    public void saveBill(){
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        String fileName = "bill.png";
        File desktopDir = Paths.get(desktopPath).toFile();
        File file = new File(desktopDir, fileName);

        WritableImage screenshot = rootPane.snapshot(new SnapshotParameters(), null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
