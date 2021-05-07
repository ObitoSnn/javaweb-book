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
import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 20:32
 */
public class ManagerBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Deprecated
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //访问数据库获取全部图书
        List<Book> books = bookService.queryBooks();
        //将图书保存到Request域中
        req.setAttribute("books", books);
        //请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //将请求参数封装成JavaBean对象
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        //向数据库添加数据
        bookService.addBook(book);
        //请求重定向，目的地址为manager/bookServlet?action=list
        //sendRedirect()：默认 --> http://localhost:8080
        //getContextPath() --> /工程名
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 通过请求参数（图书id）删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向数据库删除数据
        Integer id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);
        //请求重定向去图书列表管理页面，/Book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 通过请求参数（图书id）获取图书，将图书信息展示在book_edit.jsp页面以便于调用update()修改图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，图书的id值
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        //通过bookService获取图书信息
        //将获取到的图书信息封装成JavaBean对象保存到Request域中
        req.setAttribute("book", bookService.queryBookById(id));
        //请求转发至/pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 修改图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，封装成JavaBean对象
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        //通过bookService保存修改图书的信息
        bookService.updateBook(book);
        //请求重定向至/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 对数据库中图书分页展示
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
        Page page = bookService.getPage(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //保存到Request域数据中
        req.setAttribute("page", page);
        //请求转发，/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
