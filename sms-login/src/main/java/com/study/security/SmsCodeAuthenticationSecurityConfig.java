package com.study.security;


import com.study.security.filter.SmsCodeAuthenticationFilter;
import com.study.security.handle.CustomerAuthenticationFailureHandler;
import com.study.security.handle.CustomerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
/**
 * 下面我们需要把我们自己写的这么多类添加进 Spring Security 框架中，在以往，我们都是直接往 WebSecurityConfig 中加，但是这样会导致 WebSecurityConfig 内容太多，难以维护。
 *
 * 因此我们可以为每种登录方式都建议一个专属于它的配置文件，再把这个配置文件加入到 WebSecurityConfig 中，进行解耦。
 *
 * 在这个配置文件中，首先给 SmsCodeAuthenticationFilter 指定了：
 *
 * AuthenticationManager：不指定这个上面的流程图就断掉了。
 * 指定登录成功/失败处理逻辑，方便其父类调用。
 * 然后指定了 SmsCodeAuthenticationProvider，并指定了 UserDetailsService ，方便在验证处理时候通过 loadUserByUsername() 读取出数据库中的用户信息。
 *
 * 最后将 filter 和 provider 都加入 HttpSecurity 配置中。
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomerAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}