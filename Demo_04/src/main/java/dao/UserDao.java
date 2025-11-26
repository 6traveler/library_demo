package dao;

import entity.User;

public interface UserDao {
    //接口里的变量默认隐式声明为public static final，方法访问权限为public
    User getUserByUsername(String username);
}
