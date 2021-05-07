package com.obitosnn.servlet;

import com.google.gson.Gson;
import com.obitosnn.bean.Book;
import com.obitosnn.bean.Cart;
import com.obitosnn.bean.CartItem;
import com.obitosnn.service.BookService;
import com.obitosnn.util.SpringUtils;
import com.obitosnn.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/23 14:33
 */
public class CartServlet extends BaseServlet {
    private BookService bookService;

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService = (BookService) SpringUtils.getBean("bookService");
        //获取请求参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        //通过id查询图书信息
        Book book = bookService.queryBookById(id);
        //获取Session域中的购物车信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //修改购物车中的商品项数量
        cart.updateItem(cartItem, count);
        //请求重定向回原来的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService = (BookService) SpringUtils.getBean("bookService");
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cart.deleteItem(cartItem);
        //请求重定向回原来的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService = (BookService) SpringUtils.getBean("bookService");
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //向购物车加入商品项
        if (cart == null) {
            //Session域中无购物车
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastItemName", book.getName());
        //请求重定向回原来的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService = (BookService) SpringUtils.getBean("bookService");
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //向购物车加入商品项
        if (cart == null) {
            //Session域中无购物车
            cart = new Cart();
            //将Cart实例保存至Session域中
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //获取最后添加的商品名称
        req.getSession().setAttribute("lastItemName", book.getName());
        //将页面需要更新的数据添加至Map中
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("lastItemName", book.getName());
        map.put("totalCount", cart.getTotalCount());
        //以Json字符串的形式回传数据至客户端
        Gson gson = new Gson();
        String mapJsonString = gson.toJson(map);
        resp.getWriter().write(mapJsonString);
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            //请求重定向回原来的地址
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
