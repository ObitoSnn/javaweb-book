package com.obitosnn.dao;


import com.obitosnn.bean.Order;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:40
 */
public interface OrderDao {

    int saveOrder(Order order);

    /**
     * 查询指定用户id的所有订单
     * @param userId
     * @return
     */
    List<Order> queryOrdersByUserId(Integer userId);

    /**
     * 查询总订单数量
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询每页显示订单数量
     * @param begin
     * @param pageSize
     * @return
     */
    List<Order> queryForItems(int begin, int pageSize);

    /**
     * 通过订单号修改订单为发货状态，0未发货，1已发货，2已签收
     * @param orderId
     * @return 返回-1表示操作失败
     */
    int sendOrder(String orderId);
}
