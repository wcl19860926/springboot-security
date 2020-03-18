package com.study.security;

import com.study.entity.SysPermission;
import com.study.service.SysPermissionService;
import com.study.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 让我们修改下我们要访问的接口，@PreAuthorize("hasPermission('/admin','r')")是关键，
 * 参数1指明了访问该接口需要的url，参数2指明了访问该接口需要的权限。
 * <p>
 * 二、PermissionEvaluator
 * 我们需要自定义对 hasPermission() 方法的处理，就需要自定义 PermissionEvaluator，
 * 创建类 CustomPermissionEvaluator，实现 PermissionEvaluator 接口。
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        User user = (User) authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        System.out.println("每次请求的URL是:"+request.getRequestURL().toString());

        // 遍历用户所有角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Integer roleId = roleService.selectByName(roleName).getId();
            // 得到角色所有的权限
            List<SysPermission> permissionList = permissionService.listByRoleId(roleId);

            // 遍历permissionList
            for (SysPermission sysPermission : permissionList) {
                // 获取权限集
                List permissions = sysPermission.getPermissions();
                // 如果访问的Url和权限用户符合的话，返回true
                if (targetUrl.equals(sysPermission.getUrl())
                        && permissions.contains(targetPermission)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
