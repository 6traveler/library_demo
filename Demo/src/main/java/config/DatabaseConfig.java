package main.java.config;

/**
 * 数据库连接配置常量。
 * <p>集中维护 JDBC 驱动、URL、账号、密码，方便在不同环境中统一修改。</p>
 */
public final class DatabaseConfig {

    /**
     * MySQL 8+ 驱动类名。
     */
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * 连接字符串，默认指向本机 booklist 数据库。
     */
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/booklist?useSSL=false&characterEncoding=utf-8&serverTimezone=UTC";
    /**
     * 数据库用户名。
     */
    public static final String JDBC_USERNAME = "root";
    /**
     * 数据库密码。
     */
    public static final String JDBC_PASSWORD = "123456";

    private DatabaseConfig() {
    }
}
