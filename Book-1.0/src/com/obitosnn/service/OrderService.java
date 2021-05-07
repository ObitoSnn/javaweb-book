package com.obitosnn.service;


import com.obitosnn.bean.Cart;
import com.obitosnn.bean.Order;
import com.obitosnn.bean.OrderItem;
import com.obitosnn.bean.Page;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 17:05
 */
public interface OrderService {

     String createOrder(Cart cart, Integer userId);

    /**
     * 查看用户订单
     * @param id
     * @return
     */
    List<Order> showOrders(Integer userId);

    /**
     * 通过订单号查询订单项详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 订单分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Order> getPage(int pageNo, int pageSize);
    /**
     * 发货
     * @param orderId 订单号
     * @return 返回-1表示操作失败
     */
    int sendOrder(String orderId);
}
