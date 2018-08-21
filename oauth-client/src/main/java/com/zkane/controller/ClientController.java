package com.zkane.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 594781919@qq.com
 * @date: 2018/4/17
 */
@RestController
public class ClientController {

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
