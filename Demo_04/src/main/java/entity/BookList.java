package entity;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;
    private int bookCount;

    public BookList(ArrayList<Book> books, int bookCount) {
        this.books = books;
        this.bookCount = bookCount;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
