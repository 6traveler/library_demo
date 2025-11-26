package main.java.dao;

import main.java.entity.Book;
import main.java.util.DbUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书表 CRUD DAO，所有 SQL 操作集中于此。
 */
public class BookDao {

    private static final String BASE_COLUMNS = "id, book_name, author, count, price, isbn, content, img";

    /**
     * 查询所有图书。
     */
    public List<Book> findAll() throws SQLException {
        String sql = "SELECT " + BASE_COLUMNS + " FROM booklist ORDER BY id";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Book> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        }
    }

    /**
     * 根据主键查询单本图书。
     */
    public Book findById(long id) throws SQLException {
        String sql = "SELECT " + BASE_COLUMNS + " FROM booklist WHERE id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    /**
     * 新增图书。
     *
     * @return 自增主键
     */
    public long insert(Book book) throws SQLException {
        String sql = "INSERT INTO booklist (book_name, author, count, price, isbn, content, img) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getCount());
            ps.setBigDecimal(4, book.getPriceValue());
            ps.setString(5, book.getISBN());
            ps.setString(6, book.getContent());
            ps.setString(7, book.getImg());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return -1;
    }

    /**
     * 更新图书。
     *
     * @return 是否更新成功
     */
    public boolean update(Book book) throws SQLException {
        String sql = "UPDATE booklist SET book_name=?, author=?, count=?, price=?, isbn=?, content=?, img=? WHERE id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getCount());
            ps.setBigDecimal(4, book.getPriceValue());
            ps.setString(5, book.getISBN());
            ps.setString(6, book.getContent());
            ps.setString(7, book.getImg());
            ps.setLong(8, Long.parseLong(book.getId()));
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * 删除图书。
     */
    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM booklist WHERE id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * 将 ResultSet 中当前行映射为 Book 实体。
     */
    private Book mapRow(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(String.valueOf(rs.getLong("id")));
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setCount(rs.getInt("count"));
        BigDecimal price = rs.getBigDecimal("price");
        book.setPrice(price);
        book.setISBN(rs.getString("isbn"));
        book.setContent(rs.getString("content"));
        book.setImg(rs.getString("img"));
        return book;
    }
}

