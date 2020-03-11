package com.study.config;

import com.study.security.CustomerAuthenticationProvider;
import com.study.security.filter.CustomerAuthenticationProcessingFilter;
import com.study.security.handle.CustomerAuthenticationFailureHandler;
import com.study.security.handle.CustomerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(getCustomerAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/logout").permitAll().and()
                .formLogin().loginPage("/userlogin.html").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and().csrf().disable();


    }


    /**
     * 1、用户验证，指定多个AuthenticationProvider
     * 实际执行时候根据provider的supports方法判断是否走逻辑
     * <p>
     * 2、如果不覆盖，优先会获取AuthenticationProvider bean作为provider；
     * 如果没有bean，默认提供DaoAuthenticationProvider
     *
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customerAuthenticationProvider());
        // 未配置时候用户名密码默认登录provider
        // auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略请求 不走security filters
        web.ignoring().antMatchers("/login-error2", "/css/**", "/info", "/health", "/hystrix.stream");
    }


    @Bean
    public CustomerAuthenticationProvider customerAuthenticationProvider() {
        return new CustomerAuthenticationProvider(passwordEncoder(), userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return String.valueOf(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder myEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public CustomerAuthenticationProcessingFilter getCustomerAuthenticationProcessingFilter() throws Exception {
        CustomerAuthenticationProcessingFilter filter = new CustomerAuthenticationProcessingFilter();
        // 使用的是默认的authenticationManager
        filter.setAuthenticationManager(this.authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new CustomerAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new CustomerAuthenticationFailureHandler());
        return filter;
    }


}
