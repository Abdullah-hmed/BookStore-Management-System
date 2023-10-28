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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAuthentication {
    private Map<String, User> users;

    public UserAuthentication() {
        users = new HashMap<>();
    }

    public boolean register(User user) {
        if (users.containsKey(user.getUsername())) {
            return false; // User already exists, registration failed
        }

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        users.put(user.getUsername(), user);
        return true; // Registration successful
    }

    public boolean login(String username, String password) {
        if (!users.containsKey(username)) {
            return false; // User does not exist, login failed
        }

        User user = users.get(username);
        String hashedPassword = hashPassword(password);

        return user.getPassword().equals(hashedPassword);
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

    public static void main(String[] args) {
        UserAuthentication userAuth = new UserAuthentication();
        
        // Register a user
        User user = new User("john", "123");
        
      
        boolean registrationSuccess = userAuth.register(user);

}}

