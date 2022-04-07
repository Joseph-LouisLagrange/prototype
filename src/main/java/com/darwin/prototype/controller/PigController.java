package com.darwin.prototype.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class PigController {
    @GetMapping("/hello")
    public String hello(HttpSession session){
        return "你好";
    }
}
