package com.scdemo.myscdemo;

import com.scdemo.myscdemo.utils.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName ApplicationTaskTests
 * @Description TODO   asynchronous 异步  Synchronize 同步
 * @Author Leo
 * @Date 2019/5/22 18:33
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTaskTests {

    @Autowired
    private Task task;

    @Test
    public void testSynchronize() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }

    @Test
    public void testAsync() throws Exception {
        task.doTaskOneAsync();
        task.doTaskTwoAsync();
        task.doTaskThreeAsync();
    }


}
