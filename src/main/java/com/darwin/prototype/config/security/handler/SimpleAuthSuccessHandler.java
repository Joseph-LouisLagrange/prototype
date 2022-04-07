package com.darwin.prototype.config.security.handler;

import com.darwin.prototype.dto.LoginDto;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class SimpleAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        writer.println(LoginDto.loginSuccess().toJson());
        writer.flush();
    }
}
