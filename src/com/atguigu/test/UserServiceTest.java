package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();



    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","123456","bbj168@qq.com"));
    }

    @Test
    public void login() {
        if (userService.login(new User(null,"admin","admin",null)) == null) {
            System.out.println("登录失败,用户名或密码错误");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("wzg168111")) {
            System.out.println("用户名不可用");
        } else {
            System.out.println("用户名可用");
        }
    }
}