package com.obitosnn.service;


import com.obitosnn.bean.User;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/11 20:48
 */
public interface UserService {
    /**
     *
     * @param user
     * @return true表示用户已存在，false表示用户不存在
     */
    public boolean existsUser(String username);
    /**
     * 注册
     * @param user
     */
    public void registry(User user);
    /**
     *
     * @param user
     * @return 返回null表示登录失败
     */
    public User login(User user);

    /**
     * 通过用户名获取该用户
     * @param userName
     * @return
     */
    public User getUserByUsername(String userName);
}
