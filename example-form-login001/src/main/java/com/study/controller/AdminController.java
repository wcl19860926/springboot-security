package com.study.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get")
    public String getAdmin() {
        return "ADMIN";
    }


}
