package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.BookList;
import entity.CodeNumEntity;
import entity.ResponseMessage;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import service.Impl.BookServiceImpl;

@WebServlet("/getBookList")
public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pageNow = req.getParameter("pageNow");
        String pageSize = req.getParameter("pageSize");
        BookService bookService = new BookServiceImpl();
        BookList bookList = bookService.getBookList(pageNow, pageSize);

        //将List转换成json字符串
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> map = new HashMap<>();
        map.put("message", bookList);
        if (bookList.getBookCount() != 0) {
            //查询成功
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.SUCCESS.getCode(), CodeNumEntity.SUCCESS.getMessage(), map);
            try {
                //向前端发送数据
                //om.writeValueAsString()将respMessage转换成json字符串
                resp.getWriter().write(om.writeValueAsString(responseMessage));
                System.out.println(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (bookList.getBooks() == null) {
            //未查询到数据
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.DATABASE_ERROR.getCode(), CodeNumEntity.DATABASE_ERROR.getMessage());
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //其他异常情况
            ResponseMessage responseMessage = new ResponseMessage(CodeNumEntity.SERVER_ERROR.getCode(), CodeNumEntity.SERVER_ERROR.getMessage());
            try {
                resp.getWriter().write(om.writeValueAsString(responseMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
