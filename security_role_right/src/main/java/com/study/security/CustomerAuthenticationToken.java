package com.study.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomerAuthenticationToken extends AbstractAuthenticationToken {


     private   String userCode;
     private   String password;


    public CustomerAuthenticationToken( String userCode, String password) {
        super(null);
        this.userCode = userCode;
        this.password = password;
        setAuthenticated(false);
    }

    public CustomerAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String userCode, String password) {
        super(authorities);
        this.userCode = userCode;
        this.password = password;
    }



    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return userCode;
    }


    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.password = null;
        this.userCode = null;
    }
}
