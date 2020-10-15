package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"笔记本",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"手机",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"手机",1,new BigDecimal(1000),new BigDecimal(1000)));

        orderService.createOrder(1,cart);

    }
}