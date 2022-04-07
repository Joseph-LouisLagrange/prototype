package com.darwin.prototype.config.security.handler;

import com.darwin.prototype.dto.LoginDto;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 简单的认证错误的 Handler
 *
 */
public class SimpleAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");

        writer.println(LoginDto.loginFail(exception.getMessage()).toJson());
        writer.flush();
    }
}
