package com.obitosnn.web;



import com.obitosnn.bean.Book;
import com.obitosnn.bean.Page;
import com.obitosnn.service.BookService;
import com.obitosnn.service.impl.BookServiceImpl;
import com.obitosnn.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/19 19:12
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //封装成bean对象
        Page<Book> page = bookService.getPage(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //保存到Request域数据中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 处理价格区间的分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //封装成bean对象
        Page<Book> page = bookService.getPageByPrice(pageNo, pageSize, min, max);

        StringBuilder strUrl = new StringBuilder("client/bookServlet?action=pageByPrice");
        //判断是有最小价格参数
        if (req.getParameter("min") != null) {
            //如果有最小价格参数就追加内容至分页请求参数
            strUrl.append("&min=" + req.getParameter("min"));
        }
        //判断是有最大价格参数
        if (req.getParameter("min") != null) {
            //如果有最大价格参数就追加内容至分页请求参数
            strUrl.append("&max=" + req.getParameter("max"));
        }
        //设置分页请求地址
        page.setUrl(strUrl.toString());
        //保存到Request域数据中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
