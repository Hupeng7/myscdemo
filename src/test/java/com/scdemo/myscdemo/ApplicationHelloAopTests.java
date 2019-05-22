package com.scdemo.myscdemo;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName ApplicationHelloAopTests
 * @Description TODO
 * @Author Leo
 * @Date 2019/5/22 17:00
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationHelloAopTests {

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void test() throws Throwable {
        logger.info("输出info");
        logger.debug("输出debug");
        logger.error("输出error");
    }


}
