package com.bici.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/9
 */
@Controller
public class LoginController {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices tokenServices;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("ftl/login");
    }

    @GetMapping("/get")
    @ResponseBody
    public Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/logout")
    public ModelAndView logout(String accessToken) {
        tokenServices.revokeToken(accessToken);
        return new ModelAndView("ftl/login");
    }
}
