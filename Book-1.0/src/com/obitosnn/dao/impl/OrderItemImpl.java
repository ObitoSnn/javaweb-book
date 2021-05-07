package com.obitosnn.dao.impl;


import com.obitosnn.bean.OrderItem;
import com.obitosnn.dao.BaseDao;
import com.obitosnn.dao.OrderItemDao;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:46
 */
public class OrderItemImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) " +
                "values(?, ?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id`, `name`, `count`, `price`, `total_price` totalPrice, `order_id` orderId from t_order_item " +
                "where `order_id` = ?";
        return queryForList(sql, orderId);
    }
}
