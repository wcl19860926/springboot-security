package com.study.security.handle;


import com.alibaba.fastjson.JSONObject;
import com.study.common.pojo.Result;
import com.study.common.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Result result = new Result("登录成功", true);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
        ResponseUtils.writeStringToResponse(response, jsonObject);

    }


}
