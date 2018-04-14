package com.bici;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Sso
@SpringBootApplication
@RestController
public class ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    // sso测试接口
    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        System.out.println("auth : " + authentication);
        //Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        return authentication;
    }
}
