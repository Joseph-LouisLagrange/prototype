package com.darwin.prototype.config.security;


import com.darwin.prototype.config.security.applet.AppletAuthenticationProvider;
import com.darwin.prototype.config.security.applet.AppletLoginConfigurer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.debug.DebugFilter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;



@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    List<? extends SecurityConfigurer<DefaultSecurityFilterChain,HttpSecurity>> securityConfigurers;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable();
        http.authenticationProvider(new TestingAuthenticationProvider());
        // 加载所有的 Configurer
        loadConfigurer(http);
    }

    private void loadConfigurer(HttpSecurity http) throws Exception {
        for (SecurityConfigurer<DefaultSecurityFilterChain,HttpSecurity> securityConfigurer:securityConfigurers){
            http.apply(securityConfigurer);
        }
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager
                .createUser(User
                        .withUsername("jack")
                        .password("123456")
                        .authorities("ADMIN")
                        .build());
        return inMemoryUserDetailsManager;
    }
}
