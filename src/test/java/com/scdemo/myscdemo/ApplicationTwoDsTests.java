package com.scdemo.myscdemo;

import com.scdemo.myscdemo.domain.p.User;
import com.scdemo.myscdemo.domain.p.UserRepository;
import com.scdemo.myscdemo.domain.s.Message;
import com.scdemo.myscdemo.domain.s.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @ClassName ApplicationTwoDsTest
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/26 17:02
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTwoDsTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        userRepository.save(new User("aaa", 10, date));
        userRepository.save(new User("bbb", 20, date));
        userRepository.save(new User("ccc", 30, date));
        userRepository.save(new User("ddd", 40, date));
        userRepository.save(new User("eee", 50, date));
        Assert.assertEquals(5, userRepository.findAll().size());

        messageRepository.save(new Message("o1", "aaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbb"));
        messageRepository.save(new Message("o3", "ccccccccc"));
        Assert.assertEquals(3, messageRepository.findAll().size());
    }


}
