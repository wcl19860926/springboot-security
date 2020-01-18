package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.study.mapper")
/**
 * 实现了PermissionEvaluator之后必须添加globalMethodSecurity的注解，
 * 否则在接口上面加的权限判断不会生效。在SpringBootServletInitializer的继承类上面加上该注解启用method security。
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityTestApplication001 {


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTestApplication001.class, args);
    }
}
