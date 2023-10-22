package bookstore.application.Model;


import java.util.Random;

public class Book {
    private String bookName;
    private String bookSrc;
    private String bookAuthor;
    private int price;
    
    public Book(String bookName, String bookAuthor, String bookSrc, int price) {
        this.bookName = bookName;
        this.bookSrc = bookSrc;
        this.bookAuthor = bookAuthor;
        this.price = price;
    }
    Random random = new Random();

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSrc() {
        return bookSrc;
    }

    public void setBookSrc(String bookSrc) {
        this.bookSrc = bookSrc;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return "Book{" + "bookName=" + bookName + ", bookSrc=" + bookSrc + ", bookAuthor=" + bookAuthor + ", price=" + price + ", random=" + random + '}';
    }
    

    
}
