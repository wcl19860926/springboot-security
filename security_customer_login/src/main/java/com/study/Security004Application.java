package com.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://blog.csdn.net/yuanlaijike/article/details/80253922
 */
@SpringBootApplication
@MapperScan({"com.study.mapper"})
public class Security004Application {


    public static void main(String[] args) {
        SpringApplication.run(Security004Application.class, args);
    }
}
