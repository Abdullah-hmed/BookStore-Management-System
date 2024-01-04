package bookstore.application.Model;

import Cart.Cart;
import bookstore.application.FXMLDocumentController;
import bookstore.application.user.User;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

public class Database {
    
    private final String Url = "jdbc:mysql://2ih.h.filess.io:3307/bookstore_slopecame";
    private final String User = "bookstore_slopecame";
    private final String Password = "37f7394360cac41cc6314d198a1ba4672842b9f6";
    
    static Database Object = new Database();
    private Database() { //disabling object instantiation
    }
    public static Database getDatabase(){
        return Object;
    }

    
    
    public Connection connect() throws SQLException {
        System.out.println("Database connecting");
        return DriverManager.getConnection(Url, User, Password);
    }
    
    public List<Book> recentlyAdded() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM `books` ORDER BY `DateAdded` DESC LIMIT 5;";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {                
                bookList.add(new Book(resultSet.getInt("BookID"),resultSet.getString("BookName"),resultSet.getString("Author"),resultSet.getBytes("Picture"), resultSet.getInt("Price"),resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
    public List<Book> mostPopular() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM `books` ORDER BY `DateAdded` ASC LIMIT 5";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {                
                bookList.add(new Book(resultSet.getInt("BookID"),resultSet.getString("BookName"),resultSet.getString("Author"),resultSet.getBytes("Picture"), resultSet.getInt("Price"),resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
    public List<Book> Booklist(){
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                bookList.add(new Book(resultSet.getInt("BookID"),resultSet.getString("BookName"), resultSet.getString("Author"), resultSet.getBytes("Picture"), resultSet.getInt("Price"),resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
    public List<User> Userlist() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                userList.add(new User(resultSet.getInt("UserID"), resultSet.getString("Username"), resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("Email"), resultSet.getString("Address"),resultSet.getString("Phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
    
    
    
    public List<Cart> Orderlist() {
        List<Cart> orderList = new ArrayList<>();
        String query = "select orders.OrderID, users.Username, orders.Price, orders.OrderDate from orders JOIN users ON users.UserID = orders.UserID;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                orderList.add(new Cart(resultSet.getInt("OrderID"), resultSet.getString("Username"), resultSet.getInt("Price"), resultSet.getString("OrderDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    public List<Cart> retrieveCart() {
        System.out.println(getUserID());
        List<Cart> cartList = new ArrayList<>();
        String query = "select books.BookID, books.BookName, books.Author, books.Genre, books.Price, Cart.amount FROM books INNER JOIN Cart ON Cart.BookID = books.BookID WHERE Cart.UserID = ?;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                cartList.add(new Cart(resultSet.getInt("BookID"),resultSet.getString("BookName"), resultSet.getString("Author"),resultSet.getString("genre"),resultSet.getInt("Price"),resultSet.getInt("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }
    
    public boolean removeFromCart(int bookID){
        String cartEmptyingQuery = "Delete FROM Cart WHERE UserID = ? AND BookID = ?;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(cartEmptyingQuery)) {
            statement.setInt(1, userID);
            statement.setInt(2, bookID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addBook(File picture, String bookName, String bookAuthor, String bookGenre, float bookPrice) throws IOException{
        String selectQuery = "INSERT INTO books (BookName, Author, Genre, Price, Picture) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connect();
            FileInputStream fis = new FileInputStream(picture);
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, bookName);
            statement.setString(2, bookAuthor);
            statement.setString(3, bookGenre);
            statement.setFloat(4, bookPrice);
            statement.setBinaryStream(5, fis);
            
            int rowsAffected = statement.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Book> searchResult(String search){
        String selectQuery = "SELECT * FROM `books` WHERE BookName LIKE ?;";
        List<Book> bookList = new ArrayList<>();
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, "%"+search+"%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getInt("BookID"),resultSet.getString("BookName"), resultSet.getString("Author"), resultSet.getBytes("Picture"), resultSet.getInt("Price"),resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookList;
    }
    
    public List<Book> ByGenre(String genre) {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM books WHERE Genre = ?  ORDER BY DateAdded ASC LIMIT 5";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                bookList.add(new Book(resultSet.getInt("BookID"),resultSet.getString("BookName"), resultSet.getString("Author"), resultSet.getBytes("Picture"), resultSet.getInt("Price"),resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    
 
    public boolean doesUserExist(String username) {
        // Query the database to check if the username already exists
        String selectQuery = "SELECT COUNT(*) from users WHERE username = ?";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username); // Set the username parameter
            ResultSet result = statement.executeQuery();
        
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0; // Return true if the username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // An error occurred or username doesn't exist
    }
    
    public boolean registerUser(User user){
        String selectQuery = "INSERT INTO users (FirstName, LastName, email, username, password, Address, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getPhone()); 
            
            int rowsAffected = statement.executeUpdate();
            
            System.out.println("Signed Up!");
            
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int userID;

    public static int getUserID() {
        return userID;
    }
    
    public boolean loginUser(String username, String password) {
        String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // User authentication successful
                userID = resultSet.getInt("UserID");
                System.out.println(userID);
                FXMLDocumentController controller = FXMLDocumentController.getInstance();
                if (controller != null) {
                    controller.setUserName(username);
                    controller.loggedIn = true;
                    controller.showLogoutMenu();
                }
                
                return true;
            }else {
                // User authentication failed
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addToCart(int bookID, int amount){
        String selectQuery = "INSERT INTO Cart (UserID, BookID, amount) VALUES (?, ?, ?);";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userID);
            statement.setInt(2, bookID);
            statement.setInt(3, amount);
            
            int rowsAffected = statement.executeUpdate();
            
            System.out.println("Book Added!");
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int orderID = 0;
    public void createOrder(){
        //int orderID = 0;
        int totalCost = 0;
        List<Cart> cartList = new ArrayList<>();
        String cartRetrievalQuery = "select Cart.BookID, books.Price, amount FROM Cart JOIN books on Cart.BookID = books.BookID WHERE UserID = ?;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(cartRetrievalQuery)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                cartList.add(new Cart(resultSet.getInt("BookID"),resultSet.getInt("Price"),resultSet.getInt("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Cart cartItem : cartList) {
            totalCost += cartItem.getBookPrice() * cartItem.getAmount();
        }
        
        System.out.println("Total Cost: "+totalCost);
        
        String orderCreationQuery = "INSERT INTO orders (UserID, OrderDate, Price) VALUES (?, CURRENT_TIMESTAMP, ?)";
                
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(orderCreationQuery,PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userID);
            statement.setInt(2, totalCost);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        String OrderItemsQuery = "INSERT INTO orderitems (OrderID, BookID, amount) VALUES (?, ?, ?)";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(OrderItemsQuery)) {
            for (Cart book : cartList) {
                statement.setInt(1, orderID);
                statement.setInt(2, book.getBookID());
                statement.setInt(3, book.getAmount());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String cartEmptyingQuery = "DELETE FROM Cart WHERE UserID = ?;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(cartEmptyingQuery)) {
            statement.setInt(1, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Cart> createBill(){
        List<Cart> cartList = new ArrayList<>();
        String query = "select books.BookName, books.Price, orderitems.amount, orderitems.amount*books.Price as 'Total' FROM orderitems JOIN books ON books.BookID = orderitems.BookID JOIN orders on orderitems.OrderID = orders.OrderID JOIN users on users.UserID = orders.UserID WHERE users.UserID = ? AND orders.OrderID = ?;";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            statement.setInt(2, orderID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {                
                cartList.add(new Cart(resultSet.getString("BookName"), resultSet.getInt("Price"), resultSet.getInt("amount") ,resultSet.getInt("Total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    } 
}
