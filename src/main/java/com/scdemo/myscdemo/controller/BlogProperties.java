package com.scdemo.myscdemo.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author hupen
 */
@Component
@Data
public class BlogProperties {

    @Value("${com.myspace.blog.name}")
    private String name;
    @Value("${com.myspace.blog.title}")
    private String title;
    @Value("${com.myspace.blog.desc}")
    private String desc;
    @Value("${com.myspace.blog.value}")
    private String value;
    @Value("${com.myspace.blog.number}")
    private int number;
    @Value("${com.myspace.blog.bignumber}")
    private long bignumber;
    @Value("${com.myspace.blog.test1}")
    private int test1;
    @Value("${com.myspace.blog.test2}")
    private int test2;
    @Value("${com.myspace.blog.uuid}")
    private String uuid;

}
