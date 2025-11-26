package main.java.servlet;

import com.google.gson.Gson;
import main.java.entity.Book;
import main.java.exception.ApiException;
import main.java.service.BookService;
import main.java.util.ResponseWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 面向 SPA 的 REST 风格 Servlet，提供图书 CRUD 功能。
 */
public class BookApiServlet extends HttpServlet {

    private transient BookService bookService;
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookService = new BookService();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * GET /api/books 或 /api/books/{id}，分别返回列表与详情。
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp);
        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || "/".equals(pathInfo)) {
                List<Book> list = bookService.listBooks();
                ResponseWriter.writeSuccess(resp, list);
            } else {
                String id = extractId(pathInfo);
                Book book = bookService.getBook(id);
                ResponseWriter.writeSuccess(resp, book);
            }
        } catch (ApiException e) {
            ResponseWriter.writeError(resp, e.getHttpStatus(), e.getMessage());
        } catch (Exception e) {
            ResponseWriter.writeError(resp, 500, "未知错误：" + e.getMessage());
        }
    }

    /**
     * POST /api/books，新增图书。
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp);
        try {
            Book book = readBody(req);
            Book created = bookService.createBook(book);
            ResponseWriter.writeSuccess(resp, HttpServletResponse.SC_CREATED, created);
        } catch (ApiException e) {
            ResponseWriter.writeError(resp, e.getHttpStatus(), e.getMessage());
        } catch (Exception e) {
            ResponseWriter.writeError(resp, 500, "新增失败：" + e.getMessage());
        }
    }

    /**
     * PUT /api/books/{id}，更新图书。
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp);
        try {
            String id = extractId(req.getPathInfo());
            Book book = readBody(req);
            Book updated = bookService.updateBook(id, book);
            ResponseWriter.writeSuccess(resp, updated);
        } catch (ApiException e) {
            ResponseWriter.writeError(resp, e.getHttpStatus(), e.getMessage());
        } catch (Exception e) {
            ResponseWriter.writeError(resp, 500, "更新失败：" + e.getMessage());
        }
    }

    /**
     * DELETE /api/books/{id}，删除图书。
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCorsHeaders(resp);
        try {
            String id = extractId(req.getPathInfo());
            bookService.deleteBook(id);
            ResponseWriter.writeSuccess(resp, "删除成功");
        } catch (ApiException e) {
            ResponseWriter.writeError(resp, e.getHttpStatus(), e.getMessage());
        } catch (Exception e) {
            ResponseWriter.writeError(resp, 500, "删除失败：" + e.getMessage());
        }
    }

    /**
     * 将请求体 JSON 反序列化为 Book。
     */
    private Book readBody(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (BufferedReader reader = req.getReader()) {
            return gson.fromJson(reader, Book.class);
        }
    }

    /**
     * 从 /{id} 形式的 pathInfo 中提取数值 ID。
     */
    private String extractId(String pathInfo) {
        if (pathInfo == null || pathInfo.length() <= 1) {
            throw new ApiException(400, "缺少图书编号");
        }
        return pathInfo.substring(1);
    }

    /**
     * 统一设置 CORS 头，方便前端跨域访问。
     */
    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
    }
}

