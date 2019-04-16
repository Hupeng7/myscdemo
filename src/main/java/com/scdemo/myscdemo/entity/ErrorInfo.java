package com.scdemo.myscdemo.entity;

import lombok.Data;

/**
 * @ClassName ErrorInfo
 * @Description TODO
 * @Author hupen
 * @Date 2019/4/16 11:45
 * @Version 1.0
 */
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 200;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;

}
