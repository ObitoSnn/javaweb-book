package com.obitosnn.dao;


import com.obitosnn.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/10 23:04
 */
public interface UserDao {

    /**
     * 判断是否有该用户
     * @param username
     * @return 通过用户名查询用户
     */
    User getUserByUsername(@Param("username") String username);
    /**
     * 登录时验证用户名与密码
     * @param username
     * @param password
     * @return 通过用户名和密码查询用户
     */
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 保存用户
     * @param user
     */
    int saveUser(User user);

}
