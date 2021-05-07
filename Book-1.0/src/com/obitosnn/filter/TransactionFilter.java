package com.obitosnn.filter;

import com.obitosnn.util.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/26 21:21
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //使用 Filter 过滤器统一给所有的 Service 方法都加上 try-catch。来进行实现的管理。
        try {
            chain.doFilter(request, response);
            JDBCUtils.commitAndCloseResource();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndCloseResource();//回滚事务
            throw new RuntimeException(e);//把异常报给Tomcat管理友好页面的展示
        }
    }

    @Override
    public void destroy() {

    }
}
