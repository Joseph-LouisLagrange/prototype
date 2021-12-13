package com.darwin.prototype;

import com.darwin.prototype.doj.User;
import com.darwin.prototype.repository.UserRepository;
import com.darwin.prototype.service.PigService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.transaction.Transactional;
import java.util.Arrays;


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
