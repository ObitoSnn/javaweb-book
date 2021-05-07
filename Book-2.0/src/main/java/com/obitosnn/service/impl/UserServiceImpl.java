package com.obitosnn.service.impl;


import com.obitosnn.bean.User;
import com.obitosnn.dao.UserDao;
import com.obitosnn.service.UserService;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/11 20:55
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public boolean existsUser(String username) {
        User userByUsername = userDao.getUserByUsername(username);
        return userByUsername != null;
    }

    @Override
    public void registry(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User userByUsernameAndPassword = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userByUsernameAndPassword == null) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserByUsername(String userName) {
        return userDao.getUserByUsername(userName);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
