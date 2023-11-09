/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
  */
package bookstore.application.user;

/**
 *
 * @author Laiba Asif
 */
import bookstore.application.FXMLDocumentController;
import bookstore.application.Model.Database;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuthentication {
    private Map<String, User> users;
    //Database database = new Database();
    FXMLDocumentController controller = new FXMLDocumentController();
    public UserAuthentication() {
        users = new HashMap<>();
//        try {
//            database.connect();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void register(User user) {
        if(!usernameAvailable(user.getUsername()) && emailValidation(user.getEmail()) && passwordValidation(user.getPassword())){
            System.out.println("Making account");
            boolean registered = controller.database.registerUser(user);
            
            if(registered){
                System.out.println("Account has been registered");
            }
            else{
                System.out.println("Account did not register");
            }
        }

        
    }

    public boolean login(String username, String password) {
        return controller.database.loginUser(username, password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean emailValidation(String email){
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean passwordValidation(String password){
        return password.length() >= 5;
    }

    public boolean usernameAvailable(String username){
        return controller.database.doesUserExist(username);
    }
}

