package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.study.mapper"})
@SpringBootApplication
public class SmsLoginSecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(SmsLoginSecurityApplication.class, args);
    }
}
