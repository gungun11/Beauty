package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {

    /**
     * 根据用户名和密码查询用户
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

}
