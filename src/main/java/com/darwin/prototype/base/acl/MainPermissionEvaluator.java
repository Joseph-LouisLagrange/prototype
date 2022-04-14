package com.darwin.prototype.base.acl;

import com.darwin.prototype.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * [主] 权限执行器
 */

@Slf4j
@Component("mainPermissionEvaluator")
public class MainPermissionEvaluator implements PermissionEvaluator {

    public static final String ALL_RESOURCE = "*";

    @Resource(name = "staticPermissionChecker")
    PermissionChecker staticPermissionChecker;

    @Resource(name = "dynamicPermissionChecker")
    PermissionChecker dynamicPermissionChecker;


    @Deprecated
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable resource, String resourceType, Object permission) {
        if(ClassUtils.isAssignableValue(User.class,authentication.getDetails())){
            PermissionChecker permissionChecker;
            if(isStaticPermission(resource)){
                // 使用静态检查
                permissionChecker = staticPermissionChecker;
            }else{
                // 使用动态检查
                permissionChecker = dynamicPermissionChecker;
            }
            return permissionChecker.check(authentication,resourceType,(String) permission,resource);
        }
        return false;
    }

    public boolean isStaticPermission(Serializable resource){
        return ClassUtils.isAssignableValue(String.class,resource)
                &&(resource.equals(ALL_RESOURCE));
    }
}
