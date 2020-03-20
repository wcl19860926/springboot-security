package com.study.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    private ObjectMapper objectMapper = new ObjectMapper();
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 在 onExpiredSessionDetected() 方法中，处理相关逻辑，我这里只是简单的返回一句话。
     *
     * 执行程序，打开两个浏览器，登录同一个账户。因为我设置了 maximumSessions(1)，也就是单个用户只能存在一个 session，因此当你刷新先登录的那个浏览器时，被提示踢出了。

     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", 0);
        map.put("msg", "已经另一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest());
        // Map -> Json
        String json = objectMapper.writeValueAsString(map);

        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(json);

        // 如果是跳转html页面，url代表跳转的地址
        // redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), "url");
    }
}
