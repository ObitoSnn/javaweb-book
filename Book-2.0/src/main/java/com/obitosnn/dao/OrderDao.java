package com.obitosnn.dao;


import com.obitosnn.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:40
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);
    /**
     * 查询指定用户id的所有订单
     * @param userId
     * @return
     */
    List<Order> queryOrdersByUserId(@Param("userId") Integer userId);

    /**
     * 查询订单项
     * @param begin
     * @param pageSize
     * @return
     */
    List<Order> queryForItems(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 查询总页码数
     * @return
     */
    Integer queryForPageTotalCount();
    /**
     * 发送订单
     * @param orderId
     * @return
     */
    int sendOrder(@Param("orderId") String orderId);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryOrders();
}
