package com.starfish.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-11-08
 */
@SpringBootApplication
@MapperScan("com.starfish.test.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
