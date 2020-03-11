package com.study.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomerAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;


    private UserDetailsService userDetailsService;

    public CustomerAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomerAuthenticationToken token = (CustomerAuthenticationToken) authentication;
        try {
            UserDetails user = userDetailsService.loadUserByUsername((String) token.getPrincipal());
            if (user != null) {
                CustomerAuthenticationToken customerAuthenticationToken = new CustomerAuthenticationToken(user.getAuthorities(), user.getUsername(), user.getPassword());
                customerAuthenticationToken.setDetails(user.getAuthorities());
                return customerAuthenticationToken;
            } else {
                throw new BadCredentialsException("bad userName  passowrd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new BadCredentialsException("bad userName  passowrd");

    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomerAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
