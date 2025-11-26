package service;

import entity.Book;
import entity.BookList;

public interface BookService {
    BookList getBookList(String pageNow, String pageSize);
    Book getBookDetails(int id);
}
