package com.obitosnn.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/24 16:35
 */
public class Order {
    private String orderId;
    private BigDecimal totalPrice;
    private Integer status = 0;//0未发货，1已发货，2已签收
    private Date createTime;//创建订单的时间
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, BigDecimal totalPrice, Integer status, Date createTime, Integer userId) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createTime = createTime;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", createTime=" + createTime +
                ", userId=" + userId +
                '}';
    }
}
