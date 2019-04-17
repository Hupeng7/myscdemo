package com.scdemo.myscdemo;

import com.scdemo.myscdemo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @ClassName ApplicationUserTests
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/17 17:08
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationUserTests {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        // 准备 清空user表
        userService.deleteAllUsers();
    }

    @Test
    public void test() throws Exception {
        userService.create("a", 1, new Date());
        userService.create("b", 2, new Date());
        userService.create("c", 3, new Date());
        userService.create("d", 4, new Date());
        userService.create("e", 5, new Date());

        // 查数据库 应该有5个用户
        Assert.assertEquals(5, userService.getAllUsers().intValue());

        // 删除两个用户
        userService.deleteByName("a");
        userService.deleteByName("e");

        // 查数据库 应该有3个用户
        Assert.assertEquals(3, userService.getAllUsers().intValue());
    }
}
