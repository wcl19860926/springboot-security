package com.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
 * 如代码所示，获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
 *org.springframework.security.access.expression.SecurityExpressionRoot
 * @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);

        return "home.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }


    /**
     * org.springframework.security.access.expression.SecurityExpressionRoot
     * @return
     * hasPermission  需要自定义    PermissionEvaluator
     * org.springframework.security.access.PermissionEvaluator
     * org.springframework.security.access.expression.method.MethodSecurityExpressionRoot.hasPermission方法
     */
    @RequestMapping("/has")
    @ResponseBody
    @PreAuthorize("hasPermission('role' , 'right')")
    public String hasPermission() {
        return "如果你看见这句话，hasPermission";
    }

    //在 session 过期退出时调用
    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        return "Session 已过期，请重新登录";
    }


}
