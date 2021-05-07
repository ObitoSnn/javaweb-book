package com.obitosnn.test;


import com.obitosnn.bean.Cart;
import com.obitosnn.bean.CartItem;
import com.obitosnn.bean.Order;
import com.obitosnn.service.OrderService;
import com.obitosnn.service.impl.OrderServiceImpl;
import com.obitosnn.util.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 17:37
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "商品1", 1, new BigDecimal(888), new BigDecimal(888)));
        cart.addItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        orderService.createOrder(cart, 1);
    }
    @Test
    public void showOrders() {
        List<Order> orders = orderService.showOrders(1);
        System.out.println(orders);
    }

    @Test
    public void showOrderDetail() {
        System.out.println(orderService.showOrderDetail("16063634391511"));
    }

    @Test
    public void getPage() {
        System.out.println(orderService.getPage(1, 4));
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16070583538241");
        JDBCUtils.commitAndCloseResource();
    }
}