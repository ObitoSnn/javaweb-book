package com.obitosnn.servlet;


import com.obitosnn.bean.*;
import com.obitosnn.service.BookService;
import com.obitosnn.service.OrderService;
import com.obitosnn.service.UserService;
import com.obitosnn.util.SpringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:59
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService;
    private UserService userService;
    private BookService bookService;

    /**
     * 创建订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = (UserService) SpringUtils.getBean("userService");
        bookService = (BookService) SpringUtils.getBean("bookService");
        orderService = (OrderService) SpringUtils.getBean("orderService");
        //获取Session域中用户与购物车数据
        User user = (User) req.getSession().getAttribute("user");
        User userByUsername = userService.getUserByUsername(user.getUsername());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        for (CartItem cartItem : cart.getItems().values()) {
            Book book = bookService.queryBookById(cartItem.getId());
            if (cartItem.getCount() > book.getStock()) {
                req.setAttribute("underStockMsg", "商品【" + book.getName() + "】库存不足");
                req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp);
                return;
            }
        }
        //获取订单号
        String orderId = orderService.createOrder(cart, userByUsername.getId());

        //清空购物车
        cart.clear();

        //Session域中保存orderId
        req.getSession().setAttribute("orderId", orderId);
        //请求重定向
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
    /**
     * 展示订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = (UserService) SpringUtils.getBean("userService");
        orderService = (OrderService) SpringUtils.getBean("orderService");
        User user = (User) req.getSession().getAttribute("user");
        User userByUsername = userService.getUserByUsername(user.getUsername());
        List<Order> orders = orderService.showOrders(userByUsername.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /**
     * 展示订单详情
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService = (OrderService) SpringUtils.getBean("orderService");
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + "--->" + Arrays.asList(entry.getValue()));
            if ("orderId".equals(entry.getKey())) {
                String[] value = entry.getValue();
                String orderId = value[0];
                List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
                req.setAttribute("orderItems", orderItems);
                req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
                return;
            }
        }
    }
}
