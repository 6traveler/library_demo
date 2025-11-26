package entity;


import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private int id;
    private String book_name;
    private String author;
    private Date publish_date;
    private int pages;
    private BigDecimal price;
    private String picture;
    private String content;

    public Book() {
    }

    public Book(int id, String book_name, String author, Date publish_date, int pages, String price) {
        this.id = id;
        this.book_name = book_name;
        this.author = author;
        this.publish_date = publish_date;
        this.pages = pages;
        this.price = new BigDecimal(price);
    }

    public Book(int id, String book_name, String author, Date publish_date, int pages, String price, String picture, String content) {
        this.id = id;
        this.book_name = book_name;
        this.author = author;
        this.publish_date = publish_date;
        this.pages = pages;
        this.price = new BigDecimal(price);
        this.picture = picture;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price.toString();
    }

    public void setPrice(String  price) {
        this.price = new BigDecimal(price);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
