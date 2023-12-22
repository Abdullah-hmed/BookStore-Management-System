/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

/**
 *
 * @author Laiba Asif
 */


import bookstore.application.Model.Book;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    int BookID;
    String BookName;
    String BookAuthor;
    String BookGenre;
    int BookPrice;
    int amount;
    int totalPrice;
    int total;
    String date;

    public Cart(int BookID, String BookName, String BookAuthor, String BookGenre, int BookPrice, int amount) {
        this.BookID = BookID;
        this.BookName = BookName;
        this.BookAuthor = BookAuthor;
        this.BookGenre = BookGenre;
        this.BookPrice = BookPrice;
        this.amount = amount;
    }

    public Cart(int BookID, int BookPrice, int amount) {
        this.BookID = BookID;
        this.BookPrice = BookPrice;
        this.amount = amount;
    }

    public Cart(String BookName, int BookPrice, int amount, int total) {
        this.BookName = BookName;
        this.BookPrice = BookPrice;
        this.amount = amount;
        this.total = total;
    }
    
    public Cart(int BookID, String BookName, int BookPrice, String date){
        this.BookID = BookID;
        this.BookName = BookName;
        this.BookPrice = BookPrice;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    

    public int getTotalPrice() {
        return BookPrice*amount;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }
    
    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String BookAuthor) {
        this.BookAuthor = BookAuthor;
    }

    public String getBookGenre() {
        return BookGenre;
    }

    public void setBookGenre(String BookGenre) {
        this.BookGenre = BookGenre;
    }

    public int getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(int BookPrice) {
        this.BookPrice = BookPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
