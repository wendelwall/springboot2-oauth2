package com.bici.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/17
 */
@RestController
@RequestMapping("/login")
@Secured("ROLE_ADMIN")
public class LoginController {

    @GetMapping("/user")
    public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/index")
    @Secured("ROLE_USER1")
    public String index() {
        return "index";
    }
}
