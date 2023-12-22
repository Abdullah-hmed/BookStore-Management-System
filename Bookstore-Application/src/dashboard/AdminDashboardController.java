package dashboard;

import Cart.Cart;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import bookstore.application.user.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminDashboardController implements Initializable{

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private TableColumn<Book, Float> priceColumn;
    
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> firstname;

    @FXML
    private TableColumn<User, String> lastname;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> address;

    @FXML
    private TableColumn<User, String> phone;
    
    @FXML
    private TableView<Cart> orderTable;

    @FXML
    private TableColumn<Cart, Integer> orderID;

    @FXML
    private TableColumn<Cart, String> orderName;

    @FXML
    private TableColumn<Cart, Float> orderPrice;

    @FXML
    private TableColumn<Cart, String> orderDate;
    
    @FXML
    private TextField bookname;

    @FXML
    private TextField bookauthor;

    @FXML
    private TextField bookgenre;

    @FXML
    private TextField bookprice;

    String BookName = "";
    String BookAuthor = "";
    String BookGenre = "";
    float BookPrice = 0;
    String BookPicture = "";
    byte[] BLOBImage;
    File selectedFile;
    
    Database database = new Database();
    
    public void chooseBookImage() throws IOException{
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        selectedFile = fileChooser.showOpenDialog(new Stage());
        
        if(selectedFile != null){
            BookPicture = selectedFile.getName();
        }
    }
    
    private void configureFileChooser(FileChooser fileChooser) {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
    }
    
    private byte[] convertImageToByteArray(File imageFile) throws IOException {
        return Files.readAllBytes(imageFile.toPath());
    }
    
    public void loadBooks(){
        Task<List<Book>> task = new Task<List<Book>>() {
            @Override
            protected List<Book> call() throws Exception {
                return database.Booklist();
            }
        };

        task.setOnSucceeded(event -> {
            List<Book> bookList = task.getValue();
            ObservableList<Book> observableBooklist = FXCollections.observableArrayList(bookList);
            bookTable.setItems(observableBooklist);
        });

        new Thread(task).start();

    }
    
    public void loadUsers(){
        Task<List<User>> task = new Task<List<User>>() {
            @Override
            protected List<User> call() throws Exception {
                return database.Userlist();
            }
        };

        task.setOnSucceeded(event -> {
            List<User> userList = task.getValue();
            ObservableList<User> observableUserlist = FXCollections.observableArrayList(userList);
            userTable.setItems(observableUserlist);
        });

        new Thread(task).start();
    }
    
    private void loadOrders() {
        Task<List<Cart>> task = new Task<List<Cart>>() {
            @Override
            protected List<Cart> call() throws Exception {
                return database.Orderlist();
            }
        };

        task.setOnSucceeded(event -> {
            List<Cart> userList = task.getValue();
            ObservableList<Cart> observableOrderlist = FXCollections.observableArrayList(userList);
            orderTable.setItems(observableOrderlist);
        });

        new Thread(task).start();
    }
    
    public void addBook() throws IOException{
        BookName = bookname.getText();
        BookAuthor = bookauthor.getText();
        BookGenre = bookgenre.getText();
        if(!bookprice.getText().isEmpty()){
            BookPrice = Float.parseFloat(bookprice.getText());
        }
        System.out.println(BookPicture+", "+BookName+", "+BookAuthor+", "+BookGenre+", "+BookPrice);
        database.addBook(selectedFile, BookName, BookAuthor, BookGenre, BookPrice);
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        
        orderID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        orderName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        orderPrice.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        loadBooks();
        loadUsers();
        loadOrders();
    }

    
}
