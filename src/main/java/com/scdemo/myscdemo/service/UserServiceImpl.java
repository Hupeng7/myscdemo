package com.scdemo.myscdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/17 16:47
 * @Version 1.0
 */
@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void create(String name, Integer age, Date birthday) {
//        jdbcTemplate.update("insert into USER(NAME,AGE,BIRTHDAY) values(?,?,?)", name, age, birthday);
//    }
//
//    @Override
//    public void deleteByName(String name) {
//        jdbcTemplate.update("delete from USER where NAME = ?", name);
//    }
//
//    @Override
//    public Integer getAllUsers() {
//        return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
//    }
//
//    @Override
//    public void deleteAllUsers() {
//        jdbcTemplate.update("delete from USER");
//    }
//}


public class UserServiceImpl {

}