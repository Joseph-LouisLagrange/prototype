package com.darwin.prototype;


import com.darwin.prototype.service.inf.sys.RoleService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.lang.reflect.Array;
import java.util.*;


@SpringBootTest
class PrototypeApplicationTests {

    @Autowired
    RoleService roleService;

    @Test
    public void test(){
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/login/applet");
        MockHttpServletRequest request = new MockHttpServletRequest("GET","/login/applet");
        Assertions.assertTrue(matcher.matches(request));
    }
}
