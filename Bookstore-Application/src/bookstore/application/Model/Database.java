package bookstore.application.Model;

import bookstore.application.FXMLDocumentController;
import bookstore.application.user.User;
import java.io.ByteArrayInputStream;
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

    public int userID;

    public int getUserID() {
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
    
    public boolean addToCart(int userID, int bookID){
        String selectQuery = "INSERT INTO Cart (UserID, BookID) VALUES (?, ?);";
        
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userID);
            statement.setInt(2, bookID);
            
            int rowsAffected = statement.executeUpdate();
            
            System.out.println("Book Added!");
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
