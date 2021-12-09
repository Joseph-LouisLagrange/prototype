package com.darwin.prototype;

import com.darwin.prototype.service.PigService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class SecurityTest {

    @Autowired
    PigService pigService;

    @Test
    @WithMockUser
    public void testIsAuth(){

    }

    @Test
    @WithAnonymousUser
    public void testIsAnonymous(){
//        Assertions.assertThrows(AccessDeniedException.class, () -> pigService.fuck(),"权限生效")
//        ;
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void testPermission(){

    }
}
