package bookstore.application.Model;

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


 
}
