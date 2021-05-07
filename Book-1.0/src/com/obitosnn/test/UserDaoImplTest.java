package com.obitosnn.test;


import com.obitosnn.bean.User;
import com.obitosnn.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/10 23:19
 */
public class UserDaoImplTest {
    private UserDaoImpl dao = new UserDaoImpl();
    @Test
    public void getUserByUsername() {
//        if (dao.getUserByUsername("admin") != null) {
//            System.out.println("用户名已注册");
//        } else {
//            System.out.println("无该用户");
//        }
        System.out.println(dao.getUserByUsername("admin"));
    }
    @Test
    public void getUserByUsernameAndPassword() {
        if (dao.getUserByUsernameAndPassword("admin", "admin55") != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名或密码错误");
        }
    }

    @Test
    public void saveUser() throws Exception {
        dao.saveUser(new User(null, "spiderman", "spiderman", "spiderman@126.com"));
    }
}