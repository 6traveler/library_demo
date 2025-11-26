package main.java.service;

import main.java.dao.BookDao;
import main.java.entity.Book;
import main.java.exception.ApiException;
import main.java.exception.NotFoundException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * 图书业务层，封装所有输入校验和异常处理。
 */
public class BookService {

    private final BookDao bookDao = new BookDao();

    /**
     * 查询全部图书。
     */
    public List<Book> listBooks() {
        try {
            return bookDao.findAll();
        } catch (SQLException e) {
            throw new ApiException(500, "查询图书列表失败", e);
        }
    }

    /**
     * 根据 ID 获取图书，不存在时抛出 404。
     */
    public Book getBook(String id) {
        long parsedId = parseId(id);
        try {
            Book book = bookDao.findById(parsedId);
            if (book == null) {
                throw new NotFoundException("未找到编号为 " + id + " 的图书");
            }
            return book;
        } catch (SQLException e) {
            throw new ApiException(500, "查询图书详情失败", e);
        }
    }

    /**
     * 新增图书并返回创建结果。
     */
    public Book createBook(Book book) {
        validate(book, false);
        try {
            long id = bookDao.insert(book);
            if (id <= 0) {
                throw new ApiException(500, "新增图书失败");
            }
            return getBook(String.valueOf(id));
        } catch (SQLException e) {
            throw new ApiException(500, "新增图书失败", e);
        }
    }

    /**
     * 更新图书并返回最新数据。
     */
    public Book updateBook(String id, Book book) {
        long parsedId = parseId(id);
        validate(book, true);
        book.setId(String.valueOf(parsedId));
        ensureBookExists(parsedId);
        try {
            boolean success = bookDao.update(book);
            if (!success) {
                throw new ApiException(500, "更新图书失败");
            }
            return getBook(id);
        } catch (SQLException e) {
            throw new ApiException(500, "更新图书失败", e);
        }
    }

    /**
     * 删除图书。
     */
    public void deleteBook(String id) {
        long parsedId = parseId(id);
        ensureBookExists(parsedId);
        try {
            boolean success = bookDao.delete(parsedId);
            if (!success) {
                throw new ApiException(500, "删除图书失败");
            }
        } catch (SQLException e) {
            throw new ApiException(500, "删除图书失败", e);
        }
    }

    /**
     * 校验图书是否存在。
     */
    private void ensureBookExists(long id) {
        try {
            if (bookDao.findById(id) == null) {
                throw new NotFoundException("未找到编号为 " + id + " 的图书");
            }
        } catch (SQLException e) {
            throw new ApiException(500, "校验图书信息失败", e);
        }
    }

    /**
     * 校验新增/更新数据。
     */
    private void validate(Book book, boolean isUpdate) {
        if (book == null) {
            throw new ApiException(400, "图书信息不能为空");
        }
        handleAbnormalData(book);
        if (!isUpdate && book.getId() != null && !book.getId().isEmpty()) {
            throw new ApiException(400, "新增无需指定图书编号");
        }
        if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
            throw new ApiException(400, "书名不能为空");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new ApiException(400, "作者不能为空");
        }
        if (book.getPriceValue() == null || book.getPriceValue().signum() < 0) {
            throw new ApiException(400, "单价必须为非负数字");
        }
        if (book.getCount() < 0) {
            throw new ApiException(400, "库存数量不能为负数");
        }
        if (book.getImg() == null || book.getImg().trim().isEmpty()) {
            book.setImg("/img/红楼梦.jpg");
        }
        book.setISBN(Objects.toString(book.getISBN(), ""));
        book.setContent(Objects.toString(book.getContent(), ""));
    }

    /**
     * 拦截异常数据，避免再使用 book1 等模拟数据。
     * <p>若检测到疑似模拟数据，直接抛出 422，提示前端重新填写。</p>
     */
    private void handleAbnormalData(Book book) {
        if ("book1".equalsIgnoreCase(Objects.toString(book.getBookName(), ""))) {
            throw new ApiException(422, "检测到模拟异常数据 book1，请填写真实书名");
        }
        BigDecimal price = book.getPriceValue();
        if (price != null && price.compareTo(new BigDecimal("999999.99")) > 0) {
            throw new ApiException(422, "单价超出合理范围，请确认后重试");
        }
    }

    /**
     * 将字符串 ID 转为 long。
     */
    private long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "非法的图书编号：" + id);
        }
    }
}

