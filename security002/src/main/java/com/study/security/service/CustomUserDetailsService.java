package com.study.security.service;

import com.study.entity.SysRole;
import com.study.entity.SysUser;
import com.study.entity.SysUserRole;
import com.study.service.SysRoleService;
import com.study.service.SysUserRoleService;
import com.study.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 首先我们需要自定义 UserDetailsService ，将用户信息和权限注入进来。
 *
 * 我们需要重写 loadUserByUsername 方法，参数是用户输入的用户名。返回值是UserDetails，这是一个接口，
 * 一般使用它的子类org.springframework.security.core.userdetails.User，它有三个参数，分别是用户名、密码和权限集。
 *
 * 实际情况下，大多将 DAO 中的 User 类继承 org.springframework.security.core.userdetails.User 返回。
 *
 * 原文链接：https://blog.csdn.net/yuanlaijike/article/details/80249235
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
