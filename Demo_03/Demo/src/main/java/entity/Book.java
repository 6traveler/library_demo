package main.java.entity;


import java.math.BigDecimal;

public class Book {
    private String id;
    private String bookName;
    private String author;
    private int count;
    private BigDecimal price;
    private String ISBN;
    private String content;
    private String img;

    public Book() {
    }

    public Book(String id, String bookName, String author, int count, String price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.count = count;
        this.price = new BigDecimal(price);
    }

    public Book(String id, String bookName, String author, int count, String price, String ISBN, String content, String img) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.count = count;
        this.price = new BigDecimal(price);
        this.ISBN = ISBN;
        this.content = content;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price.toString();
    }

    public void setPrice(String  price) {
        this.price = new BigDecimal(price);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
