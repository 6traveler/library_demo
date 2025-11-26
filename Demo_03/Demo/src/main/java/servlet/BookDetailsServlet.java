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

public class BookDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //允许跨域的主机地址
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("字符编码出错");
        }
        resp.setContentType("text/html;charset=utf-8");

        String id = req.getParameter("id");


        List<Book> bookList = new ArrayList<>();
        //用book1去模拟异常数据情况
        //Book book1 = new Book("1", "红楼梦", "曹雪芹", 57, "40.68", "9787802506077", "《红楼梦》，原名《石头记》，中国古代章回体长篇小说，中国古典四大名著之一。" +
        //        "其通行本共120回，一般认为前80回是清代作家曹雪芹所著，后40回作者为无名氏，整理者为程伟元、高鹗。小说以贾、史、王、薛四大家族的兴衰为背景，以富贵公子贾宝玉为视角，以贾宝玉与林黛玉、薛宝钗的爱情婚姻悲剧为主线，" +
        //        "描绘了一些闺阁佳人的人生百态，展现了真正的人性美和悲剧美，是一部从各个角度展现女性美以及中国古代社会百态的史诗性著作。", "../img/红楼梦.jpg");
        Book book2 = new Book("2", "西游记", "吴承恩", 62, "42.81", "9787532512003", "《西游记》是明代吴承恩创作的中国古代第一部浪漫主义章回体长篇神魔小说。" +
                "该小说主要讲述了孙悟空出世跟随菩提祖师学艺及大闹天宫后，遇见了唐僧、猪八戒、沙僧和白龙马，西行取经，一路上历经艰险，降妖除魔，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事。" +
                "该小说以“玄奘取经”这一历史事件为蓝本，经作者的艺术加工，深刻地描绘出明代百姓的社会生活状况。", "../img/西游记.jpg");
        Book book3 = new Book("3", "三国演义", "罗贯中", 53, "41.25", "9787510136740", "《三国演义》全名为《三国志通俗演义》，又称《三国志演义》）是元末明初小说家罗贯中根据陈寿《三国志》" +
                "和裴松之注解以及民间三国故事传说经过艺术加工创作而成的长篇章回体历史演义小说，是中国文学史上第一部章回小说，是历史演义小说的开山之作，也是第一部文人长篇小说，明清时期甚至有“第一才子书”之称。自问世以来，取材于它的各类文艺作品不胜枚举。" +
                "它的巨大影响力，以至于使艺术的真实盖过了历史的真实。", "../img/三国演义.jpg");
        Book book4 = new Book("4", "水浒传", "施耐庵", 55, "45.18", "9787020015016", "《水浒传》是元末明初施耐庵（现存刊本署名大多有施耐庵、罗贯中两人中的一人，或两人皆有）编著的章回体长篇小说。" +
                "全书通过描写梁山好汉反抗欺压、水泊梁山壮大和受宋朝招安，以及受招安后为宋朝征战，最终消亡的宏大故事，艺术地反映了中国历史上宋江起义从发生、发展直至失败的全过程，深刻揭示了起义的社会根源，满腔热情地歌颂了起义英雄的反抗斗争和他们的社会理想，" +
                "也具体揭示了起义失败的内在历史原因。", "../img/水浒传.jpg");
        //bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);

        //从booklist里遍历，查找相应id的图书详情信息
        for (int i = 0; i < bookList.size(); i++) {
            //向前端发送数据
            Map<String,Object> map = new HashMap<>();
            //将查看图书信息转换为json格式
            Gson gson = new Gson();
            //模拟异常处理问题，如果找到该数据，则正常返回
            if(id.equals(bookList.get(i).getId())) {
                try {
                    map.put("message", bookList.get(i));
                    ResponseMessage respMessage = new ResponseMessage("200", map);
                    resp.getWriter().write(gson.toJson(respMessage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            //如果找不到数据，则返回服务器异常
            if(i == bookList.size() - 1){
                try {
                    map.put("message","服务器异常");
                    ResponseMessage respMessage = new ResponseMessage("500",map);
                    resp.getWriter().print(gson.toJson(respMessage));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
