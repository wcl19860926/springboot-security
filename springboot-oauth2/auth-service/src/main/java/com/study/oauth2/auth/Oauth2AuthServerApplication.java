package com.study.oauth2.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer //开启资源服务，因为程序需要对外暴露获取token的API接口
@EnableEurekaClient //开启Eureka Client
public class Oauth2AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthServerApplication.class, args);
    }
}
