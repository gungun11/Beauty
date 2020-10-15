package com.atguigu.service;

import com.atguigu.pojo.Cart;

public interface OrderService {
    /**
     * 生成订单
     * @param userId
     * @param cart
     */
    public String createOrder(Integer userId, Cart cart);

}
