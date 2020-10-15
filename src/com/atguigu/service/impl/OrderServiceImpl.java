package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Integer userId, Cart cart) {

        System.out.println( " OrderServiceImpl 线程名是 => " + Thread.currentThread().getName() );

        String orderId = System.currentTimeMillis() + "" + userId;
        // 保存订单
        orderDao.saveOrder(new Order(orderId,new Date(),0,cart.getToatlPrice(), userId));

        int i = 12 / 0 ;

        // 遍历购物车中的商品项，转换为订单项去保存
        for (CartItem cartItem : cart.getItems().values()) {
            // 把购物车商品项，转换为订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            // 保存订单项
            orderItemDao.saveOrderItem(orderItem);

            // 先查询出购物的图书信息
            Book book = bookDao.queryBookById(cartItem.getId());
            // 修改库存和销量
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());

            bookDao.updateBook(book);
        }

        // 下单后，一般购物车就会被清空
        cart.clear();


        return orderId;
    }

}
