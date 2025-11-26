package main.java.entity;


import java.math.BigDecimal;

/**
 * 图书实体，承载数据库 booklist 表中的每一条记录。
 */
public class Book {
    private String id;
    private String bookName;
    private String author;
    private int count;
    private BigDecimal price = BigDecimal.ZERO;
    private String ISBN;
    private String content;
    private String img;

    /**
     * 默认构造器，便于 Gson / JDBC 等框架进行反射赋值。
     */
    public Book() {
    }

    /**
     * 仅包含基础字段的辅助构造器。
     */
    public Book(String id, String bookName, String author, int count, String price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.count = count;
        setPrice(price);
    }

    /**
     * 包含详情信息的辅助构造器。
     */
    public Book(String id, String bookName, String author, int count, String price, String ISBN, String content, String img) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.count = count;
        setPrice(price);
        this.ISBN = ISBN;
        this.content = content;
        this.img = img;
    }

    /**
     * @return 图书编号
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 图书编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * @return 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return 库存数量
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count 库存数量
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return 字符串形式的价格
     */
    public String getPrice() {
        return price == null ? "0" : price.toPlainString();
    }

    /**
     * @return BigDecimal 形式的价格，方便持久化
     */
    public BigDecimal getPriceValue() {
        return price;
    }

    /**
     * 通过字符串设置价格，自动处理空值与格式。
     */
    public void setPrice(String price) {
        if (price == null || price.trim().isEmpty()) {
            this.price = BigDecimal.ZERO;
            return;
        }
        this.price = new BigDecimal(price);
    }

    /**
     * 通过 BigDecimal 设置价格，空值时默认 0。
     */
    public void setPrice(BigDecimal price) {
        this.price = price == null ? BigDecimal.ZERO : price;
    }

    /**
     * @return ISBN 码
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN ISBN 码
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return 图书简介
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content 图书简介
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return 封面图路径
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img 封面图路径
     */
    public void setImg(String img) {
        this.img = normalizeImgPath(img);
    }

    /**
     * 统一封面图路径，兼容 ./img 与 /img。
     */
    private String normalizeImgPath(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        if (trimmed.startsWith("./")) {
            return trimmed.substring(1);
        }
        return trimmed;
    }
}
