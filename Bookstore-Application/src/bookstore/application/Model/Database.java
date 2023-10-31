package bookstore.application.Model;

import bookstore.application.user.User;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class Database {
    
    private final String Url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12656987";
    private final String User = "sql12656987";
    private final String Password = "SkJW9AUzZ5";
    

    
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(Url, User, Password);
    }
    
    public List<Book> recentlyAdded() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM `books` ORDER BY `DateAdded` DESC LIMIT 5;";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {                
                bookList.add(new Book(resultSet.getString("BookName"),resultSet.getString("Author"),resultSet.getBytes("Picture"), resultSet.getInt("Price")));
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
                bookList.add(new Book(resultSet.getString("BookName"),resultSet.getString("Author"),resultSet.getBytes("Picture"), resultSet.getInt("Price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
<<<<<<< HEAD
    public List<Book> ByGenre(String genre) {
    List<Book> bookList = new ArrayList<>();
    String query = "SELECT * FROM `books` WHERE `Genre` =   ORDER BY `DateAdded` ASC LIMIT 5";

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(query)) {
        
        statement.setString(1, genre);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {                
            bookList.add(new Book(resultSet.getString("BookName"), resultSet.getString("Author"), resultSet.getBytes("Picture"), resultSet.getInt("Price")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return bookList;
}


 
=======
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
        String selectQuery = "INSERT INTO users (username, password, email, FirstName, LastName, Address, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstname());
            statement.setString(5, user.getLastname());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getPhone()); 
            
            int rowsAffected = statement.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginUser(String username, String password) {
        String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


>>>>>>> 18b7d00d44a031d923cbeaf134e1a07af67f5080
}
