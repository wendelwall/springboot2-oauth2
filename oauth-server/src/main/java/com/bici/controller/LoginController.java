package com.bici.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/9
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("ftl/login");
    }
}
