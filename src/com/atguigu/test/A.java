package com.atguigu.test;

public class A {

    public void servletMethod(){
        new B().serviceMethod();
    }

    public static void main(String[] args) {
        try {
            new A().servletMethod();
        } catch (Exception e) {

        }
    }

}
