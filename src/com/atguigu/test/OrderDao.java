package com.atguigu.test;

public class OrderDao {
    public void saveOrder(){
        String name = Thread.currentThread().getName();
        Object o =  ThreadLocalTest.threadLocal.get(); //ThreadLocalTest.map.get(name);
        System.out.println(" OrderDao [" + name + "] => " + o);
    }
}
