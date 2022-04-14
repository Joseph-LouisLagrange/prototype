package com.darwin.prototype.base.acl;

import com.darwin.prototype.po.User;
import org.springframework.security.core.Authentication;
import org.springframework.util.ClassUtils;

public abstract class AbstractPermissionChecker implements PermissionChecker {

    protected User getUser(Authentication authentication){
        if(ClassUtils.isAssignableValue(User.class,authentication.getDetails())){
            return (User) authentication.getDetails();
        }
        return null;
    }

}
