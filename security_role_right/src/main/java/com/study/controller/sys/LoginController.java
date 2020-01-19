package com.study.controller.sys;

import com.study.common.pojo.Result;

import com.study.enitity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys")
public class LoginController {


    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static String SESSION_KEY_USER_CODE = "session_key_user_code";



    private AuthenticationManager authenticationManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(HttpServletRequest request, String username, String password) {
        Result result = new Result();
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            request.getSession().setAttribute(SESSION_KEY_USER_CODE, ((SysUser) authenticationResult.getDetails()).getUserCode());
            result.setIsSuccess(true);
            result.setMsg("登录成功！");
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setMsg("登录失败！");
        }
        return result;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result login(HttpServletRequest request) {
        Result result = new Result();
        request.removeAttribute(SESSION_KEY_USER_CODE);
        request.getSession().invalidate();
        result.setIsSuccess(true);
        result.setMsg("退出登录成功！");
        return result;
    }

}
