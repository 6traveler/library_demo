package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Book;
import entity.CodeNumEntity;
import entity.ResponseMessage;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getBookDetails")
public class BookDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //使用jackson包将数据转换为json格式
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        //如果前端传输的id为空，返回操作失败-----要先判定参数是否合法（不合法的参数可能会导致业务层代码出现异常，所以在处理业务之前先判参）
        if (req.getParameter("id").equals("")) {
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.FAIL.getCode(), CodeNumEntity.FAIL.getMessage());
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //获取前端传输的id参数
        int id = Integer.parseInt(req.getParameter("id"));
        //获取书籍信息
        BookService bookService = new BookServiceImpl();
        Book book = bookService.getBookDetails(id);
        //如果查询不到图书，则返回数据库错误
        if (book == null) {
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.DATABASE_ERROR.getCode(), CodeNumEntity.DATABASE_ERROR.getMessage());
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //如果查询到图书，则正确返回信息
        if (book != null) {
            map.put("message", book);
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.SUCCESS.getCode(), CodeNumEntity.SUCCESS.getMessage(), map);
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //其它情况返回服务器错误
        else {
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.SERVER_ERROR.getCode(), CodeNumEntity.SERVER_ERROR.getMessage());
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
