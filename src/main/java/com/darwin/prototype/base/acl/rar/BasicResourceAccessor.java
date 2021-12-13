package com.darwin.prototype.base.acl.rar;

import com.darwin.prototype.base.acl.AbstractResourceAccessor;
import com.darwin.prototype.doj.User;
import com.google.common.collect.Sets;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * 通用资源访问器
 * 实现 基础 action 控制
 */
public abstract class BasicResourceAccessor extends AbstractResourceAccessor {
    private final ResourceTypeMatch resourceTypeMatch;

    public BasicResourceAccessor(ResourceTypeMatch resourceTypeMatch){
        this.resourceTypeMatch = resourceTypeMatch;
    }

    @Override
    public boolean supportResourceType(String resourceType) {
        return resourceTypeMatch.match(resourceType);
    }

    @Override
    public boolean isAgree(User user, String action, Serializable resource) {
        if (isMultiple(resource)){
            Set<Long> resourceIDs = multipleToSet(resource, Long.class);
            return isAgreeAtResourceIDs(user,action,resourceIDs);
        }
        if (ClassUtils.isAssignableValue(Long.class,resource)){
            return isAgreeAtResourceID(user,action, (Long) resource);
        }
        return false;
    }

    public boolean isAgreeAtResourceID(User user,String action,long resourceID){
       return isAgreeAtResourceIDs(user,action,Sets.newHashSet(resourceID));
    }

    public boolean isAgreeAtResourceIDs(User user,String action,Set<Long> resourceIDs){
        switch (action){
            case ACTION_READ:return read(user,resourceIDs);
            case ACTION_CREATE:return create(user,resourceIDs);
            case ACTION_DELETE:return delete(user,resourceIDs);
            case ACTION_UPDATE:return update(user,resourceIDs);
        }
        return false;
    }

    public abstract boolean read(User user, Set<Long> resourceIDs);
    public abstract boolean create(User user, Set<Long> resourceIDs);
    public abstract boolean delete(User user, Set<Long> resourceIDs);
    public abstract boolean update(User user, Set<Long> resourceIDs);
}
