package com.darwin.prototype.base.acl.rar;

import com.darwin.prototype.base.acl.ResourceAccessor;
import com.darwin.prototype.po.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("denyResourceAccessor")
public class DenyResourceAccessor implements ResourceAccessor {
    @Override
    public boolean supportResourceType(String resourceType) {
        return false;
    }

    @Override
    public boolean isAgree(User user, String action, Serializable resource) {
        return false;
    }
}
