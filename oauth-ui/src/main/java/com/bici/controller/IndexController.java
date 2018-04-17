package com.bici.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/17
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
