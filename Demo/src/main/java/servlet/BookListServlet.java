package main.java.servlet;

import main.java.entity.Book;
import main.java.exception.ApiException;
import main.java.service.BookService;
import main.java.util.ResponseWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 兼容旧页面的列表接口，现从数据库真实读取数据。
 */
public class BookListServlet extends HttpServlet {

    private transient BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 处理旧版 AJAX 请求，返回数据库图书列表
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // 保持兼容旧逻辑，仅打印提示
            System.out.println("字符编码设置失败：" + e.getMessage());
        }
        resp.setContentType("application/json;charset=utf-8");

        try {
            List<Book> bookList = bookService.listBooks();
            ResponseWriter.writeSuccess(resp, bookList);
        } catch (ApiException e) {
            try {
                ResponseWriter.writeError(resp, e.getHttpStatus(), e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * POST 调用保持与历史前端兼容，内部直接转入 GET。
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
