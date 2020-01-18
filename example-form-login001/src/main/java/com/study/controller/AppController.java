package com.study.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/api/")

public class AppController {


    @GetMapping("/get")
    public String getAppName() {
        return "APP";
    }
}
