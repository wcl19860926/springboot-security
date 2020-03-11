package com.study.security.filter;

import com.study.security.CustomerAuthenticationToken;
import com.study.security.handle.CustomerAuthenticationFailureHandler;
import com.study.security.handle.CustomerAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomerAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "userCode";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String VALIDATE_CODE = "validateCode";

    private boolean postOnly = true;


    public CustomerAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/sys/user/login", null));
        setAuthenticationFailureHandler(new CustomerAuthenticationFailureHandler());
        setAuthenticationSuccessHandler(new CustomerAuthenticationSuccessHandler());


    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String userCode = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
            String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
            String validateCode = request.getParameter(VALIDATE_CODE);
            if (userCode == null) {
                userCode = "";
            }

            if (password == null) {
                password = "";
            }

            userCode = userCode.trim();
            password = password.trim();
            CustomerAuthenticationToken authRequest = new CustomerAuthenticationToken(userCode, password);
            authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
            return this.getAuthenticationManager().authenticate(authRequest);
        }

    }

}
