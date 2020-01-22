package com.study.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api/")
public class UserController {

    @GetMapping("/get")
    public String getUserName() {
        return "USER";
    }




    @GetMapping("/list")
    public String gtUser() {
        return "userList";
    }
}
