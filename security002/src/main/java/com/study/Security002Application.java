package com.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://blog.csdn.net/yuanlaijike/article/details/80249235
 */
@SpringBootApplication
@MapperScan({"com.study.mapper"})
public class Security002Application {


    public static void main(String[] args) {
        SpringApplication.run(Security002Application.class, args);
    }
}
