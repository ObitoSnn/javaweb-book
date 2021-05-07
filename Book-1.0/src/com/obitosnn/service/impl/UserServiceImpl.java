package com.obitosnn.service.impl;

import com.obitosnn.bean.User;
import com.obitosnn.dao.UserDao;
import com.obitosnn.dao.impl.UserDaoImpl;
import com.obitosnn.service.UserService;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/11 20:55
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public boolean existsUser(String username) {
        if (dao.getUserByUsername(username) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void registry(User user) {
        dao.saveUser(user);
    }

    @Override
    public User login(User user) {
        if (dao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            return null;
        }
        return user;
    }
}
