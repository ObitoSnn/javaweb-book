package com.obitosnn.test;


import com.obitosnn.bean.User;
import com.obitosnn.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/11 21:11
 */
public class UserServiceTest {
    private UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void existsUser() {
        if (userService.existsUser("admin22")) {
            System.out.println("用户名已存在");
        } else {
            System.out.println("用户名可用");
        }
    }

    @Test
    public void registry() {
        userService.registry(new User(null, "obito7990", "obitosnn", "obito799@gmail.com"));
    }

    @Test
    public void login() {
        if (userService.login(new User(null, "obitosnn", "obitosnn", null)) == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("登录成功");
        }
    }
}