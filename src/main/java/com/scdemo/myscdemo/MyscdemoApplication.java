package com.scdemo.myscdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hupen
 */
@SpringBootApplication
@EnableSwagger2
public class MyscdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyscdemoApplication.class, args);
	}

}
