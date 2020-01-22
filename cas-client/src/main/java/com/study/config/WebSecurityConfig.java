package com.study.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity(debug = true)
public class WebSecurityConfig   extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationProvider  authenticationProvider;

    @Autowired
    private AuthenticationEntryPoint  authenticationEntryPoint;

    @Autowired
    private SingleSignOutFilter  singleSignOutFilter;

    @Autowired
    private LogoutFilter requestSingleLogoutFilter;

    @Autowired
    private CasAuthenticationFilter casAuthenticationFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("user/**").hasRole("USER")
               .antMatchers("/login/cas","/favicon.icon","/error").permitAll()
               .anyRequest().authenticated()
               .and()
               .exceptionHandling()
               .authenticationEntryPoint(authenticationEntryPoint)
               .and()
               .addFilter(casAuthenticationFilter)
               .addFilterBefore(singleSignOutFilter ,CasAuthenticationFilter.class)
               .addFilterBefore(requestSingleLogoutFilter ,LogoutFilter.class);



















    }
}
