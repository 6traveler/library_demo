package main.java.servlet;

import com.google.gson.Gson;
import main.java.entity.Book;
import main.java.entity.ResponseMessage;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        //允许跨域的主机地址
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("字符编码出错");
        }
        resp.setContentType("text/html;charset=utf-8");

        List<Book> bookList=new ArrayList<>();
        Book book1=new Book("1", "红楼梦", "曹雪芹", 57, "40.68");
        Book book2=new Book("2", "西游记", "吴承恩", 62, "42.81");
        Book book3=new Book("3", "三国演义", "罗贯中", 53, "41.25");
        Book book4=new Book("4", "水浒传", "施耐庵", 55, "45.18");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);

        //将List转换成json字符串
        Gson gson=new Gson();

        Map<String,Object> map = new HashMap<>();
        map.put("message", bookList);
        ResponseMessage respMessage = new ResponseMessage("200", map);

        try {
            //向前端发送数据
            //gson.toJson()将respMessage转换成json字符串
            resp.getWriter().write(gson.toJson(respMessage));
            System.out.println(gson.toJson(respMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
