package com.pf.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author: Ru He
 * Date: Created in 2019/1/7.
 * Description:
 */
@SpringBootApplication
@MapperScan("com.pf.**.dao")
@ComponentScan("com.pf")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
