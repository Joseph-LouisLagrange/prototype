package com.darwin.prototype.base.acl;

import com.darwin.prototype.po.User;

import java.io.Serializable;

/**
 * 资源访问者
 * 作为动态权限检验的分发对象
 */
public interface ResourceAccessor {
    String ACTION_READ = "read";

    String ACTION_CREATE = "create";

    String ACTION_UPDATE = "update";

    String ACTION_DELETE = "delete";

    /**
     * 判断对该资源的支持情况
     * @param resourceType 资源类型
     * @return 是否支持该资源
     */
    boolean supportResourceType(String resourceType);

    /**
     * 是否能进行操作特定操作
     * @param user 用户（操作者）
     * @param action 实施动作（INSERT,DELETE...）
     * @param resource 资源实体或者特定集合
     * @return 是否同意进行操作
     */
    boolean isAgree(User user, String action, Serializable resource);
}
