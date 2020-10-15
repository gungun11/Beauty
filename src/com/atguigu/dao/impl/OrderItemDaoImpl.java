package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        System.out.println( " OrderItemDaoImpl 线程名是 => " + Thread.currentThread().getName() );
        String sql = "insert into t_order_item(`name`,`price`,`total_price`,`count`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice()
                ,orderItem.getCount(),orderItem.getOrderId());
    }
}
