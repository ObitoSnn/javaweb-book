package com.obitosnn.dao.impl;


import com.obitosnn.bean.Order;
import com.obitosnn.dao.BaseDao;
import com.obitosnn.dao.OrderDao;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:41
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `total_price`, `status`, `create_time`, `user_id`) " +
                "values(?, ?, ?, ?, ?)";
        return update(sql, order.getOrderId(), order.getTotalPrice(), order.getStatus(), order.getCreateTime(),
                order.getUserId());
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId, `total_price` totalPrice, `status`, `create_time` createTime, `user_id` userId from t_order " +
                "where `user_id` = ?";
        return queryForList(sql, userId);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_order";
        Number count = (Number) getValue(sql);
        return count.intValue();
    }

    @Override
    public List<Order> queryForItems(int begin, int pageSize) {
        String sql = "select `order_id` orderId, `total_price` totalPrice, `status`, `create_time` createTime, `user_id` userId from t_order limit ?, ?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public int sendOrder(String orderId) {
        String sql = "update t_order set `status` = 1 where `order_id` = ?";
        int updateCount = update(sql, orderId);
        if (updateCount > 0) {
            return updateCount;
        }
        return -1;
    }

}
