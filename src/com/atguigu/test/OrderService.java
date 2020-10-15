package com.atguigu.test;

public class OrderService {

    public void createOrder(){
        String name = Thread.currentThread().getName();
        Object o = ThreadLocalTest.threadLocal.get(); //ThreadLocalTest.map.get(name);
        System.out.println(" OrderService [" + name + "] => " + o);
        new OrderDao().saveOrder();
    }

}
