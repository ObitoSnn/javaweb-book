package com.obitosnn.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/23 13:24
 */
public class Cart {
//    private Integer totalCount;//总商品数量
//    private BigDecimal totalPrice;//商品总金额
    private Map<Integer, CartItem> items = new LinkedHashMap<>();//购物车商品项

    /**
     * 添加商品商品项
     * @param cartItem
     * @return 返回1添加成功，返回-1添加失败
     */
    public int addItem(CartItem cartItem) {
        //判断是否已添加过此商品
        CartItem item = items.get(cartItem.getId());
        if (item != null) {
            //添加过
            item.setCount(item.getCount() + 1);//设置购物车商品项数量
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//设置购物车商品项总金额
            return 1;
        } else if (item == null){
            //没有添加过
            items.put(cartItem.getId(), cartItem);
            return 1;
        }
        return -1;
    }

    /**
     * 删除商品项
     * @param cartItem
     * @return 返回1删除成功，返回-1删除失败
     */
    public int deleteItem(CartItem cartItem) {
        for (CartItem value : items.values()) {
            if (cartItem.getId().equals(value.getId())) {
                items.remove(cartItem.getId());
                return 1;
            }
        }
        return -1;
    }

    /**
     * 修改商品项数量
     * @param cartItem 待修改的商品项
     * @param count 待修改的数量
     * @return 返回1修改成功，返回-1修改失败
     */
    public int updateItem(CartItem cartItem, Integer count) {
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            if (entry.getKey().equals(cartItem.getId())) {
                //购物车有该商品
                entry.getValue().setCount(count);//修改商品数量
                BigDecimal totalPrice = cartItem.getPrice().multiply(new BigDecimal(entry.getValue().getCount()));
                entry.getValue().setTotalPrice(totalPrice);//修改商品总价
                return 1;
            }
        }
        return -1;
    }

    /**
     * 清空购物车商品项
     */
    public void clear() {
        items.clear();
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : items.values()) {
            totalPrice = totalPrice.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
        return totalPrice;
    }
    public Map<Integer, CartItem> getItems() {
        return items;
    }
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
