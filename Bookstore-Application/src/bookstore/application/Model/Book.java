package bookstore.application.Model;
public class Book {
    private String bookName;
    private String bookSrc;
    private String bookAuthor;

    public Book(String bookName, String bookAuthor, String bookSrc) {
        this.bookName = bookName;
        this.bookSrc = bookSrc;
        this.bookAuthor = bookAuthor;
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
    

}
