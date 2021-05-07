package com.obitosnn.dao.impl;


import com.obitosnn.bean.User;
import com.obitosnn.dao.BaseDao;
import com.obitosnn.dao.UserDao;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/10 23:08
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    /**
     *
     * @param username
     * @return 如果返回null则表示无该用户
     */
    @Override
    public User getUserByUsername(String username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        return queryForOne(sql, username);
    }
    /**
     *
     * @param username
     * @param password
     * @return 如果返回null则表示用户名或密码错误
     */
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(sql, username, password);
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into t_user(`username`, `password`, `email`) values(?, ?, ?)";
        update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
