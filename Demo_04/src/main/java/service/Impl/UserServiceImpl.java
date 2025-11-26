package service.Impl;

import dao.Impl.UserDaoImpl;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDaoImpl userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    //在service层判定用户是否存在并且验证登录是否成功
    @Override
    public User login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
