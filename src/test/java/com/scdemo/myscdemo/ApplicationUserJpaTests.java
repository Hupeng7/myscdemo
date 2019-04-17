package com.scdemo.myscdemo;

import com.scdemo.myscdemo.entity.User;
import com.scdemo.myscdemo.service.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @ClassName ApplicationUserJpaTests
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/17 18:29
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationUserJpaTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        // 创建10条记录
        Date now = new Date();
        userRepository.save(new User("AAA", 10, now));
        userRepository.save(new User("BBB", 20, now));
        userRepository.save(new User("CCC", 30, now));
        userRepository.save(new User("DDD", 40, now));
        userRepository.save(new User("EEE", 50, now));
        userRepository.save(new User("FFF", 60, now));
        userRepository.save(new User("GGG", 70, now));
        userRepository.save(new User("HHH", 80, now));
        userRepository.save(new User("III", 90, now));
        userRepository.save(new User("JJJ", 100, now));

        // 测试findAll 查询所有记录
        Assert.assertEquals(10, userRepository.findAll().size());

        // 测试findByName 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

        // 测试findByUser 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

        // 测试findByNameAndAge 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

        // 测试删除姓名为AAA的User
        userRepository.delete(userRepository.findByName("AAA"));

        // 测试findAll 查询所有记录 验证上面删除的是否成功
        Assert.assertEquals(9, userRepository.findAll().size());

    }


}
