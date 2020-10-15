package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

public interface OrderItemDao {
    /**
     * 保存订单项
     */
    public int saveOrderItem(OrderItem orderItem);

}
