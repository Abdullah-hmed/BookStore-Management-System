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
    private List<Book> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addToCart(Book book) {
        items.add(book);
    }

    public void removeFromCart(Book book) {
        items.remove(book);
    }

    public List<Book> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }


}
