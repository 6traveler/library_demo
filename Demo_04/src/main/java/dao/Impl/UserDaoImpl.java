package dao.Impl;

import dao.UserDao;
import entity.User;
import util.JDBCConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    //通过用户名获取用户信息
    @Override
    public User getUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //建立连接
            conn = JDBCConnect.getConnection();
            String sql = "select * from user where user_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            JDBCConnect.release(conn,ps,rs);
        }
        return null;
    }
}
