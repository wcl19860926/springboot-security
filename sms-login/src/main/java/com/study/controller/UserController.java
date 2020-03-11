package com.study.controller;

import com.study.enitity.SysUser;
import com.study.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 查看登录session里的信息
     * @param authentication
     * @return
     */
    @GetMapping("/me")
    @ResponseBody
    public Object me(Authentication authentication) {
        return authentication;
    }


    /**
     * 根据userCode查询用户信息
     * @param userCode
     * @return
     */
    @GetMapping("/{userCode}")
    @ResponseBody
    public SysUser me(@PathVariable(name = "userCode") String userCode) {
        return sysUserService.findSysUserByCode(userCode);
    }


}
