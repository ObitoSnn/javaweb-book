package com.obitosnn.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 14:47
 */
public abstract class BaseServlet extends HttpServlet {
    private static WebApplicationContext ctx;

    /**
     * 供SpringUtils获取WebApplicationContext实例，确保使用同一个容器中的对象
     * @return
     */
    public static WebApplicationContext getCtx() {
        return ctx;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 使用监听器把容器对象(spring容器)放入到全局作用域ServletContext中。
         * 从ServletContext中获取WebApplicationContext
         */
        //方式一
//        String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
//        Object attr = getServletContext().getAttribute(key);
//        if (attr != null) {
//            ctx = (WebApplicationContext) attr;
//        }
        //方式二，使用工具类
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
