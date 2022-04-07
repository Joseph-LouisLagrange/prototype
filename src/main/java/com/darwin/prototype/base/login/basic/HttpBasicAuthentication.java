package com.darwin.prototype.base.login.basic;

import com.darwin.prototype.base.login.HttpSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class HttpBasicAuthentication implements HttpSecurityConfigurer {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }
}
