package com.darwin.prototype.service.impl.sys;

import com.darwin.prototype.po.sys.Permission;
import com.darwin.prototype.po.sys.Role;
import com.darwin.prototype.exception.BaseExceptionType;
import com.darwin.prototype.exception.CommonException;
import com.darwin.prototype.repository.sys.RoleRepository;
import com.darwin.prototype.service.inf.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Set<Permission> getPermissionsOfRole(long roleID) {
        Role role = roleRepository.findById(roleID)
                .orElseThrow(()->CommonException.of(BaseExceptionType.MISS,MISS_ROLE_BY_ID,"角色 ID 未命中"));
        return role.getPermissions();
    }

    @Override
    public Set<Permission> getPermissionsOfRole(String roleName) {
        Role role = roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> CommonException.of(BaseExceptionType.MISS, MISS_ROLE_BY_NAME, "角色名未命中"));
        return role.getPermissions();
    }
}
