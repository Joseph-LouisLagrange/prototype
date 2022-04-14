package com.darwin.prototype.config.security;


import com.darwin.prototype.base.login.HttpSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    List<? extends SecurityConfigurer<DefaultSecurityFilterChain,HttpSecurity>> securityConfigurers;

    @Autowired
    List<HttpSecurityConfigurer> httpSecurityConfigurers;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable();
        http.authenticationProvider(new TestingAuthenticationProvider());
        for (HttpSecurityConfigurer httpSecurityConfigurer:httpSecurityConfigurers){
            httpSecurityConfigurer.configure(http);
        }
        // 加载所有的 Configurer
         loadConfigurer(http);
    }


    private void loadConfigurer(HttpSecurity http) throws Exception {
        for (SecurityConfigurer<DefaultSecurityFilterChain,HttpSecurity> securityConfigurer:securityConfigurers){
            http.apply(securityConfigurer);
        }
    }
}
