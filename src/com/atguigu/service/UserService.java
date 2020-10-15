package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {

    /**
     * 注册用户
     *
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     *
     * @param username
     * @return 返回true, 表示用户名已存在, 不可用 <br/>
     * 返回false,表示用户名可用
     */
    public boolean existsUsername(String username);

}
