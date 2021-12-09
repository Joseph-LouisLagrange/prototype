package com.darwin.prototype.config.security;

import com.darwin.prototype.doj.sys.Permission;
import com.darwin.prototype.service.inf.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * [主] 权限执行器
 */

@Component
@Slf4j
public class MainPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    RoleService roleService;


    @Deprecated
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // 获取所有的角色
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            Set<Permission> permissionsOfRole = roleService.getPermissionsOfRole(authority.getAuthority());
            ;
        }
        return false;
    }
}
