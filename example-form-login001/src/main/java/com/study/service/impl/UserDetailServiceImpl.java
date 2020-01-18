package com.study.service.impl;

import com.study.entity.User;
import com.study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user ;
        try {
             user = userMapper.findUserByUserName(userName);
            user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return user;
    }
}
