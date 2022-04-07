package com.darwin.prototype;


import com.darwin.prototype.dto.LoginDto;
import com.darwin.prototype.service.inf.sys.RoleService;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
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

    public List<String> findRepeatedDnaSequences(String s) {
        int N = (int) (1e5 + 10);
        int P = 0;
        int[] h = new int[N];
        int[] p = new int[N];
        p[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            h[i] = h[i-1] * P + s.charAt(i-1);
            p[i] = p[i-1] * P;
        }
        List<String> ans = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 1; (i-1+10) <= s.length(); i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i-1]*p[j-i+1];
            int cnt = map.getOrDefault(hash, 0);
            if (cnt==1){
                ans.add(s.substring(i-1,j));
            }
            map.put(hash,cnt+1);
        }
        return ans;
    }


    @Test
    public void test(){

        System.out.println(new Gson().toJson(LoginDto.loginFail("你好")));
    }
}
