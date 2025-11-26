package dao;

import entity.Book;
import entity.BookList;

public interface BookDao {
    BookList selectBookList(int start, int pageSize);
    Book selectBookDetails(int id);
}
