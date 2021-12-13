package com.darwin.prototype.base.acl;

import com.darwin.prototype.doj.User;
import com.darwin.prototype.doj.sys.Permission;
import com.darwin.prototype.doj.sys.Role;
import com.darwin.prototype.service.inf.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;



@Component("staticPermissionChecker")
public class StaticPermissionChecker extends AbstractPermissionChecker {

    @Autowired
    RoleService roleService;

    public Set<Permission> getPermissions(Authentication authentication){
        User user = getUser(authentication);
        if (user==null){
            return authentication.getAuthorities().stream()
                    .parallel()
                    .map(GrantedAuthority::getAuthority)
                    .map(roleName -> roleService.getPermissionsOfRole(roleName))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
        return user.getRole().stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    @Override
    public boolean check(Authentication authentication, String resourceType, String action, Serializable resource) {
        Set<Permission> permissions = getPermissions(authentication);
        return permissions.stream()
                .parallel()
                .anyMatch(p-> p.isAgree(resourceType,action,(String) resource));
    }
}
