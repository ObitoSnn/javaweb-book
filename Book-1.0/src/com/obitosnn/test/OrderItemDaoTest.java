package com.obitosnn.test;


import com.obitosnn.bean.OrderItem;
import com.obitosnn.dao.OrderItemDao;
import com.obitosnn.dao.impl.OrderItemImpl;
import com.obitosnn.util.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:55
 */
public class OrderItemDaoTest {
    private OrderItemDao orderItemDao = new OrderItemImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderitem = new OrderItem(null, "spider", 2, new BigDecimal(10), new BigDecimal(20),
                "12345");
        orderItemDao.saveOrderItem(orderitem);
        JDBCUtils.commitAndCloseResource();
    }

    @Test
    public void queryOrderItemByOrderId() {
        System.out.println(orderItemDao.queryOrderItemByOrderId("16063634391511"));
    }
}