package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCConnect {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();

        //通过类加载器读取对应的资源
        InputStream is = JDBCConnect.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //建立数据库连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return null;
    }

    //释放连接
    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }

        if (conn != null) {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }


}
