package com.obitosnn.dao;


import com.obitosnn.bean.OrderItem;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:45
 */
public interface OrderItemDao {

     int saveOrderItem(OrderItem orderItem);

    /**
     * 通过订单号查询订单项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
