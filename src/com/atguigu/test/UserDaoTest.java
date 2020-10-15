package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsernameAndPassword() {
        User admin = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if (admin == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        userDao.saveUser(new User(null,"wzg168","666666","wzg168@qq.com"));
        userDao.saveUser(new User(null,"aaa168","123456","aaa168@qq.com"));
    }

    @Test
    public void queryUserByUsername() {
        User wzg168 = userDao.queryUserByUsername("wzg16888");
        if (wzg168 == null) {
            System.out.println(" 用户名可用! ");
        }else {
            System.out.println("用户名已存在,不可用");
        }
    }
}