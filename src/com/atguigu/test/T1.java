package com.atguigu.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class T1 {

    @Test
    public void test() {
        //1 map有多个key, ThreadLocal固定key
        //2 map没有初始值 , ThreadLocal可以通过方法重写方法给定初始值
        //3 hashTable中的数据需要你自己手动clear清空.而ThreadLocal它会随着线程的消失.自动释放内存
        //4 hashTable的方法加锁,ThreadLocal存取数据没有加锁操作
        Map<String,Object> map = new HashMap<>();
        System.out.println( map.get("abc") );
        map.clear();

        ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
            // 初始值
            @Override
            protected Object initialValue() {
                return "abc";
            }
        };
        System.out.println( threadLocal.get() );
    }

}
