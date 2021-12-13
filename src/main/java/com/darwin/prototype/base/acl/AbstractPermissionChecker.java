package com.darwin.prototype.base.acl;

import com.darwin.prototype.base.PermissionChecker;
import com.darwin.prototype.doj.User;
import com.darwin.prototype.doj.sys.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.util.ClassUtils;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPermissionChecker implements PermissionChecker {

    protected User getUser(Authentication authentication){
        if(ClassUtils.isAssignableValue(User.class,authentication.getDetails())){
            return (User) authentication.getDetails();
        }
        return null;
    }

}
