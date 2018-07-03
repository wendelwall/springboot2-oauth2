package com.bici.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/17
 */
@RestController
@RequestMapping("/client")
@Secured("ROLE_ADMIN")
public class ClientController {

    @GetMapping("/user")
    public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/index")
    @Secured("ROLE_USER")
    public String index() {
        return "index";
    }
}
