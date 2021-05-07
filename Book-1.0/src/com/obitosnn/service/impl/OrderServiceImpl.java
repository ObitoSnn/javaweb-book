package com.obitosnn.service.impl;


import com.obitosnn.bean.*;
import com.obitosnn.dao.BookDao;
import com.obitosnn.dao.OrderDao;
import com.obitosnn.dao.OrderItemDao;
import com.obitosnn.dao.impl.BookDaoImpl;
import com.obitosnn.dao.impl.OrderDaoImpl;
import com.obitosnn.dao.impl.OrderItemImpl;
import com.obitosnn.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 17:06
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //orderId唯一-->时间戳+用户id
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, cart.getTotalPrice(), 0, new Date(), userId);
        //保存订单
        orderDao.saveOrder(order);

        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            CartItem cartItem = entry.getValue();
            //将购物车每一个商品项转换为订单项
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(),
            cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);
            //修改图书销量与库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        return orderId;
    }

    @Override
    public List<Order> showOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public Page<Order> getPage(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();
        //设置显示数据数量
        page.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = orderDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //获取总页码
        Integer pageTotal = pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        //数据有效边境检查
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > page.getPageTotal()) {
            pageNo = page.getPageTotal();
        }
        //设置当前页码
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Order> items = orderDao.queryForItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.sendOrder(orderId);
    }
}
