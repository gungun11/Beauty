package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"爱啥啥",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));

        System.out.println(cart);

    }

    @Test
    public void deleteItem() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"爱啥啥",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(2);

        System.out.println(cart);

    }

    @Test
    public void updateCount() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"爱啥啥",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(2);

        cart.updateCount(1, 10);

        System.out.println(cart);
    }

    @Test
    public void clear() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"爱啥啥",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"爱谁谁",1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(2);

        cart.updateCount(1, 10);

        cart.clear();

        System.out.println(cart);

    }


}