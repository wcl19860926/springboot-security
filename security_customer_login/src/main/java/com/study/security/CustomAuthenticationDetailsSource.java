package com.study.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 该接口用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充
 * Z 自定义了WebAuthenticationDetails，我i们还需要将其放入 AuthenticationDetailsSource
 * 中来替换原本的 WebAuthenticationDetails ，因此还得实现自定义 AuthenticationDetailsSource ：
 * <p>
 * <p>
 * 该类内容将原本的 WebAuthenticationDetails 替换为了我们的 CustomWebAuthenticationDetails。
 * <p>
 * 然后我们将 CustomAuthenticationDetailsSource 注入Spring Security中，替换掉默认的 AuthenticationDetailsSource。
 * <p>
 * 修改 WebSecurityConfig，将其注入，然后在config()中使用 authenticationDetailsSource(authenticationDetailsSource)方法来指定它。
 */
@Component("authenticationDetailsSource")
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }
}
