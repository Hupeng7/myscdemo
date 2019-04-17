package com.scdemo.myscdemo.service;

import java.util.Date;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/17 16:35
 * @Version 1.0
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param name
     * @param age
     */
    void create(String name, Integer age, Date birthday);

    /**
     * 根据nama删除一个用户
     *
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();


}
