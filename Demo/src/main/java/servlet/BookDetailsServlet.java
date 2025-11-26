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

/**
 * 兼容旧页面的详情接口，真实读取数据库并提供统一异常处理。
 */
public class BookDetailsServlet extends HttpServlet {

    private transient BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 读取单本图书详情，兼容旧版查询接口
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("字符编码出错");
        }
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
                try {
                ResponseWriter.writeError(resp, 400, "缺少图书编号");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return;
            }

        try {
            Book book = bookService.getBook(id.trim());
            ResponseWriter.writeSuccess(resp, book);
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
}
