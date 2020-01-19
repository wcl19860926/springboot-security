package com.study.security.handle;

import com.alibaba.fastjson.JSONObject;
import com.study.common.pojo.Result;
import com.study.common.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        Result result = new Result("登录失败！", true);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
        ResponseUtils.writeStringToResponse(response, jsonObject);
    }
}
