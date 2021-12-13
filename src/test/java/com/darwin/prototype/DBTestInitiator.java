package com.darwin.prototype;

import com.darwin.prototype.doj.sys.Permission;
import com.darwin.prototype.doj.sys.PermissionExpression;
import com.darwin.prototype.doj.sys.Role;
import com.darwin.prototype.repository.PermissionRepository;
import com.darwin.prototype.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class DBTestInitiator {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    /**
     * 初始化 ACL 数据表信息
     */
    @Rollback(value = false)
    @Transactional
    @Test
    public void initACL(){
        Role testAdmin = Role.of("TEST_ADMIN");
        Permission permission = Permission.of(PermissionExpression.parse("Pig:create:*"));
        permissionRepository.saveAndFlush(permission);
        testAdmin.getPermissions().add(permission);
        roleRepository.saveAndFlush(testAdmin);
    }
}
