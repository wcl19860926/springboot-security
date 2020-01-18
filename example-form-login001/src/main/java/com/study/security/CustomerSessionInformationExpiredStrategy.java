package com.study.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * maximumSessions(int)：指定最大登录数
 * maxSessionsPreventsLogin(boolean)：是否保留已经登录的用户；为true，新用户无法登录；为 false，旧用户被踢出
 * expiredSessionStrategy(SessionInformationExpiredStrategy)：旧用户被踢出后处理方法
 * 设置了 maximumSessions(1)，也就是单个用户只能存在一个 session，
 * maxSessionsPreventsLogin(true) 时的情况，我们发现第一个浏览器登录后，第二个浏览器无法登录：
 */
public class CustomerSessionInformationExpiredStrategy  implements SessionInformationExpiredStrategy {


    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","已经另一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest());
        String s = objectMapper.writeValueAsString(map);
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(s);
    }
}
