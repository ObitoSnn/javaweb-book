package com.obitosnn.servlet;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.obitosnn.bean.User;
import com.obitosnn.service.UserService;
import com.obitosnn.util.SpringUtils;
import com.obitosnn.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/13 23:25
 */
public class UserServlet extends BaseServlet {
    private UserService userService;

    /**
     * Ajax异步请求判断用户名是否可用
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = (UserService) SpringUtils.getBean("userService");
        String username = req.getParameter("username");
        boolean existsUser = userService.existsUser(username);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("existsUser", existsUser);

        Gson gson = new Gson();
        String mapJsonString = gson.toJson(map);
        resp.getWriter().write(mapJsonString);
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除Session中用户信息（或销毁Session）
        //方式一
        //request.getSession().removeAttribute("user");
        //方式二
        request.getSession().invalidate();
        //请求重定向至主页面
        //getContextPath()：http://localhost:8080/工程路径
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService = (UserService) SpringUtils.getBean("userService");
        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());
        if (userService.login(new User(null, user.getUsername(), user.getPassword(), null)) != null) {
            //登录成功
            request.getSession().setAttribute("user", user);
            //跳转至登录成功页面
            if ("root".equals(user.getUsername())) {
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
            }
        } else {
            //登录失败
            //将错误信息与要回显的信息保存的request域对象中
            request.setAttribute("msg", "用户名或密码错误！");
            //跳转至登录页面
            if ("root".equals(user.getUsername())) {
                request.getRequestDispatcher("/pages/manager/login_manager.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        }
    }
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService = (UserService) SpringUtils.getBean("userService");
        String token = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");
        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());
        if (token.equalsIgnoreCase(code)) {
            //验证码正确
            if (userService.existsUser(user.getUsername())) {
                //用户名已存在
                //跳转至注册界面
                System.out.println("用户名[" + user.getUsername() + "]已存在");
                //将回显数据保存到request域中
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("email", user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //用户不存在
                //注册
                userService.registry(user);
                request.getSession().setAttribute("user", user);
                //请求转发至注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        } else {
            //验证码错误
            //将回显数据保存到request域中
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword());
            //请求转发至注册界面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }
}
