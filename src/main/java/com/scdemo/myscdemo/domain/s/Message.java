package com.scdemo.myscdemo.domain.s;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName Message
 * @Description TODO
 * @Author Leo
 * @Date 2019/4/23 20:44
 * @Version 1.0
 */

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Message() {
    }
}
