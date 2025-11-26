package main.java.entity;

/**
 * 用户实体，当前项目仅用于演示登录验证。
 */
public class User {
    private String username;
    private String password;

    /**
     * 默认构造器
     */
    public User(){

    }

    /**
     * @param username 登录名
     * @param password 登录密码
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
