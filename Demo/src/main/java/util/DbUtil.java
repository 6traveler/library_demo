package main.java.util;

import main.java.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 连接工具类，集中处理驱动加载与连接获取。
 */
public final class DbUtil {

    static {
        try {
            Class.forName(DatabaseConfig.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("无法加载数据库驱动，请确认已引入 MySQL JDBC 依赖", e);
        }
    }

    private DbUtil() {
    }

    /**
     * 获取一个新的数据库连接，调用方按需关闭。
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.JDBC_URL,
                DatabaseConfig.JDBC_USERNAME,
                DatabaseConfig.JDBC_PASSWORD
        );
    }
}

