package dao.Impl;

import dao.BookDao;
import entity.Book;
import entity.BookList;
import util.JDBCConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookDaoImpl implements BookDao {
    //获取图书列表
    @Override
    public BookList selectBookList(int start, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Book> books = new ArrayList<>();
        try {
            //建立数据库连接
            conn = JDBCConnect.getConnection();
            String sql = "select * from book limit " + start + "," + pageSize;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //查询记录  将查找出来的数据装入ArrayList<Book>
            while (rs.next()){
                int id=rs.getInt("id");
                String book_name = rs.getString("book_name");
                String author = rs.getString("author");
                Date publish_date = rs.getDate("publish_date");
                int pages = rs.getInt("pages");
                String price = rs.getString("price");
                Book book = new Book(id, book_name, author, publish_date, pages, price);
                books.add(book);
            }

            //查询符合条件的记录条数,sql语句优化器默认用主键索引查询
            sql = "select count(*) from book ";
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            BookList booklist = new BookList(books, count);

            return booklist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放数据库连接
            JDBCConnect.release(conn, ps, rs);
        }
        return null;
    }

    //根据id查询数据库内图书详情信息
    @Override
    public Book selectBookDetails(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //建立数据库连接
            conn = JDBCConnect.getConnection();
            String sql = "select * from book where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBook_name(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setPublish_date(rs.getDate("publish_date"));
                book.setPages(rs.getInt("pages"));
                book.setPrice(rs.getString("price"));
                book.setPicture(rs.getString("picture"));
                book.setContent(rs.getString("content"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放数据库连接
            JDBCConnect.release(conn, ps, rs);
        }
        return null;
    }
}
