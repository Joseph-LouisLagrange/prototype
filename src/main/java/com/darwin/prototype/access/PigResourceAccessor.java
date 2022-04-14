package com.darwin.prototype.access;

import com.darwin.prototype.base.acl.rar.BasicResourceAccessor;
import com.darwin.prototype.base.acl.rar.SimpleResourceTypeMatch;
import com.darwin.prototype.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Set;

@Slf4j
@Component("pigResourceAccessor")
public class PigResourceAccessor extends BasicResourceAccessor {
    public PigResourceAccessor() {
        super(new SimpleResourceTypeMatch("Pig","com.xxx.pojo.Pig"));
    }

    @Override
    // @AccessControl("read") @Accessor @AccessResource
    public boolean read( User user, Set<Long> resourceIDs) {
        log.debug("执行 read user:{} resourceIDs:{}",user,resourceIDs);
        return true;
    }

    @Override
    public boolean create(User user, Set<Long> resourceIDs) {
        log.debug("执行 create user:{} resourceIDs:{}",user,resourceIDs);
        return false;
    }

    @Override
    public boolean delete(User user, Set<Long> resourceIDs) {
        log.debug("执行 delete user:{} resourceIDs:{}",user,resourceIDs);
        return false;
    }

    @Override
    public boolean update(User user, Set<Long> resourceIDs) {
        log.debug("执行 update user:{} resourceIDs:{}",user,resourceIDs);
        return false;
    }

}
