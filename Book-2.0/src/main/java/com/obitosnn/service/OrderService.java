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
    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);
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
     * 获取商品项的分页对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Order> getPage(int pageNo, int pageSize);

    /**
     * 发送订单
     * @param orderId
     * @return
     */
    int sendOrder(String orderId);
}
