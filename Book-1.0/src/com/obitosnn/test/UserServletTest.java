package com.obitosnn.test;

import java.lang.reflect.Method;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 0:08
 */
public class UserServletTest {

    public void login() {
        System.out.println("login()被调用了");
    }

    public void regist() {
        System.out.println("regist()被调用了");
    }

    public void updateUser() {
        System.out.println("updateUser()被调用了");
    }

    public static void main(String[] args) throws Exception {
        String action = "updateUser";
        Method method = UserServletTest.class.getDeclaredMethod(action);
        method.invoke(new UserServletTest());
    }

}
