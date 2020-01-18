package com.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class LoginController {



    @RequestMapping("/login/invalid")
    @PreAuthorize("hasPermission('user', 'read') or hasRole('USER')")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalid() {
        return "Session 已过期，请重新登录";
    }


}
