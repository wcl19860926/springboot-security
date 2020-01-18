package com.study.security;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security 提供了两种处理配置，一个是 invalidSessionStrategy()，另外一个是 invalidSessionUrl()。
 *
 * 这两个的区别就是一个是前者是在一个类中进行处理，后者是直接跳转到一个 Url。简单起见，
 * 我就直接用 invalidSessionUrl()了，跳转到 /login/invalid，我们需要把该 Url 设置为免授权访问，
 */
public class CustomerInvalidSessionStrategy  implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
