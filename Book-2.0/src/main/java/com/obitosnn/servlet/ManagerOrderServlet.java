package com.obitosnn.servlet;


import com.obitosnn.bean.Order;
import com.obitosnn.bean.OrderItem;
import com.obitosnn.bean.Page;
import com.obitosnn.service.OrderService;
import com.obitosnn.util.SpringUtils;
import com.obitosnn.util.WebUtils;

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
 * @Date 2020/12/4 下午12:42
 */
public class ManagerOrderServlet extends BaseServlet {
    private OrderService orderService;


    /**
     * 发货
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService = (OrderService) SpringUtils.getBean("orderService");
        //订单号
        String orderId = req.getParameter("orderId");
        //发货
        orderService.sendOrder(orderId);
        //请求重定向至原来的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 订单分页展示
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService = (OrderService) SpringUtils.getBean("orderService");
        //获取请求参数，pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //封装成bean对象
        Page<Order> page = orderService.getPage(pageNo, pageSize);
        page.setUrl("manager/orderServlet?action=page");
        //保存到Request域数据中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
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
                req.getRequestDispatcher("/pages/manager/order_detail_manager.jsp").forward(req, resp);
                return;
            }
        }
    }
}
