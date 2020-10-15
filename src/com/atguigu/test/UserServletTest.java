package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {

    public void login(){
        System.out.println("有客人 上门了");
    }

    public void regist(){
        System.out.println(" 客人翻版了 ");
    }

    public void updatePassword(){
        System.out.println(" 忘了门 ");
    }

    public static void main(String[] args) throws Exception {
        String action = "login";
        /**
         * getDeclaredMethod()获取指定名称的方法反射对象
         * 第一个参数是方法名 <br/>
         * 第二个参数是方法的参数类型列表
         */
        Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);

//        System.out.println(declaredMethod);
        /**
         * invoke()执行method方法<br/>
         * 第一个参数是对象实例
         * 第二个参数是调用方法时传递的参数<br/>
         */
        declaredMethod.invoke(new UserServletTest());
    }



}
