package com.atguigu.test;

import java.util.Random;

public class ThreadLocalTest {

//    public static Map<String,Integer> map = new Hashtable();
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
//    public  static ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
    private  static Random random = new Random();

    static class Task implements Runnable{
        @Override
        public void run() {
            // 在线程开始的时候,获取一个随机数,并在当前线程名做为key保存到map中
            int i = random.nextInt(100);
            String name = Thread.currentThread().getName();
            System.out.println(" 当前线程[" + name + "] 生成的随机数是 => " + i);
//            map.put(name,i);
//            threadLocal.set(i);

//            threadLocal.set(100);
//            threadLocal.set(1000);

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            new OrderService().createOrder();

            // 在线程快消失的时候,再从map中以线程名为key获取出相应的值
//            Object o = map.get(name);
            Integer o = threadLocal.get();
            System.out.println("线程[" + name + "] 结束时取出是 => " + o);
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }

    }

}
