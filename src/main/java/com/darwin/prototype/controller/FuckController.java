package com.darwin.prototype.controller;

import org.apache.catalina.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class FuckController {
    @GetMapping("/hello")
    public String hello(HttpSession session){
        session.setAttribute("fuck","you");
        return "hello";
    }
}
