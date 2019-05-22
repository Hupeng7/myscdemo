package com.scdemo.myscdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hupen
 */
@SpringBootApplication
@EnableSwagger2
@RestController
@EnableAsync
public class MyscdemoApplication {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testLogLevel() {
        logger.debug("Logger Level: DEBUG");
        logger.info("Logger Level : INFO");
        logger.error("Logger Level: ERROR");
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyscdemoApplication.class, args);
    }

//    @RestController
//    class HelloController {
//        @PostMapping("/user")
//        public UserDto user(@RequestBody UserDto userDto) {
//            return userDto;
//        }
//    }

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class UserDto {
//        private String userName;
//        private LocalDate birthday;
//    }

    /**
     * 在配置了依赖之后，我们只需要在上面的应用主类中增加这个序列化模块，并禁用对日期以时间戳方式输出的特性
     * 就可以使得日期格式正常显示
     */
    @Bean
    public ObjectMapper serialisingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
