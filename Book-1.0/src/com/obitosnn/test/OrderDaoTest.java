package com.obitosnn.test;


import com.obitosnn.bean.Order;
import com.obitosnn.dao.OrderDao;
import com.obitosnn.dao.impl.OrderDaoImpl;
import com.obitosnn.util.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:49
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("12345", new BigDecimal(99), 0, new Date(), 1);
        orderDao.saveOrder(order);
        JDBCUtils.commitAndCloseResource();
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        System.out.println(orders);
        JDBCUtils.commitAndCloseResource();
    }

    @Test
    public void queryForItems() {
        List<Order> orders = orderDao.queryForItems(0, 4);
        System.out.println(orders);
    }

    @Test
    public void sendOrder() {
        int updateCount = orderDao.sendOrder("16070583538241");
        System.out.println(updateCount);
        if (updateCount != -1) {
            System.out.println("发货成功");
            //切记要提交事务并关闭资源
            JDBCUtils.commitAndCloseResource();
        }
    }
}