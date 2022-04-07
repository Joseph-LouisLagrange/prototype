package com.darwin.prototype.base.login;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface HttpSecurityConfigurer {
    void configure(HttpSecurity http) throws Exception;
}
