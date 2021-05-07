package com.obitosnn.test;


import com.obitosnn.bean.Cart;
import com.obitosnn.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/23 14:07
 */
public class CartTest {
    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "商品1", 1, new BigDecimal(888), new BigDecimal(888)));
        cart.addItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "商品1", 1, new BigDecimal(888), new BigDecimal(888)));
        cart.addItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println(cart);
        cart.deleteItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println("删除商品2" + cart);
    }

    @Test
    public void updateItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "商品1", 1, new BigDecimal(888), new BigDecimal(888)));
        cart.addItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println(cart);
        cart.deleteItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println("删除商品2" + cart);
        cart.updateItem(new CartItem(1, "商品1", 1, new BigDecimal(3000), new BigDecimal(3000)), 2);
        System.out.println("商品更新之后" + cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "商品1", 1, new BigDecimal(888), new BigDecimal(888)));
        cart.addItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println(cart);
        cart.deleteItem(new CartItem(2, "商品2", 1, new BigDecimal(999), new BigDecimal(999)));
        System.out.println("删除商品2" + cart);
        cart.updateItem(new CartItem(1, "商品1", 1, new BigDecimal(3000), new BigDecimal(3000)), 2);
        System.out.println("商品更新之后" + cart);
        cart.clear();
        System.out.println("清空购物车" + cart);
    }
}