package com.bici.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/17
 */
@RestController
public class LoginController {
    // sso测试接口
    @GetMapping("/user")
    public Authentication getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
