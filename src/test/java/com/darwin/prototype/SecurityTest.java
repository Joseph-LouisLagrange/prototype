package com.darwin.prototype;

import com.darwin.prototype.po.User;
import com.darwin.prototype.service.PigService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;


@SpringBootTest
public class SecurityTest {

    @Autowired
    PigService pigService;

    @Test
    @WithMockUser(authorities = "TEST_ADMIN")
    public void testStaticPermission(){
        Assertions.assertThrows(AccessDeniedException.class,()-> pigService.getAll());
        Assertions.assertDoesNotThrow(()->pigService.create());
        Assertions.assertThrows(AccessDeniedException.class,()-> pigService.deleteAll());
    }


    public void initUser(){
        TestingAuthenticationToken token = new TestingAuthenticationToken(0,0);
        token.setAuthenticated(true);
        token.setDetails(User.of("jj",45));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    public void testDynamicPermission(){
        initUser();
        Assertions.assertDoesNotThrow(()-> pigService.getByID(1L));
        Assertions.assertThrows(AccessDeniedException.class,()->pigService.update(new long[]{1}));
    }
}
