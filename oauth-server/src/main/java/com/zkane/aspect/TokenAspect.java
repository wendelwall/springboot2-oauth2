package com.zkane.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: 594781919@qq.com
 * @review:
 * @date: 2018/8/24
 */
@Aspect
@Component
public class TokenAspect {

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    private void token() {
    }

    @AfterReturning(returning = "obj", pointcut = "token()")
    public void doAfterReturning(ResponseEntity<OAuth2AccessToken> obj) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 打印用户名
        System.out.println(request.getParameter("username"));
        // 打印token
        System.out.println(obj.getBody().getValue());
    }
}
