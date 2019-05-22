package com.scdemo.myscdemo.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName Task
 * @Description TODO
 * @Author Leo
 * @Date 2019/5/22 18:28
 * @Version 1.0
 */
@Component

public class Task {

    public static Random random = new Random();

    public void doTaskOne() throws Exception {
        System.out.println("start task one");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task one,time is: " + (end - start) + " ms");
    }

    public void doTaskTwo() throws Exception {
        System.out.println("start task two");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task two,time is: " + (end - start) + " ms");
    }

    public void doTaskThree() throws Exception {
        System.out.println("start task three");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task three,time is: " + (end - start) + " ms");
    }

    @Async
    public void doTaskOneAsync() throws Exception {
        System.out.println("start task one");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task one,time is: " + (end - start) + " ms");
    }

    @Async
    public void doTaskTwoAsync() throws Exception {
        System.out.println("start task two");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task two,time is: " + (end - start) + " ms");
    }

    @Async
    public void doTaskThreeAsync() throws Exception {
        System.out.println("start task three");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("finish task three,time is: " + (end - start) + " ms");
    }

}
