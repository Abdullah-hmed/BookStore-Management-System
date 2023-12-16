package bookstore.application.Model;


import java.util.Random;

public class Book {
    private int bookID;
    private String bookName;
    private byte[] bookSrc;
    private String bookAuthor;
    private int price;
    private String genre;
    
    public Book(int bookID, String bookName, String bookAuthor, byte[] bookSrc, int price , String genre) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookSrc = bookSrc;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.genre=genre;
    }

    public int getBookID() {
        return bookID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public byte[] getBookSrc() {
        return bookSrc;
    }

    public void setBookSrc(byte[] bookSrc) {
        this.bookSrc = bookSrc;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    
    public void GetData(){
        System.out.println("Name: "+getBookName()+"\nAuthor: "+getBookAuthor()+"\nGenre: "+getGenre()+"\nPrice: "+getPrice());
    }
}
