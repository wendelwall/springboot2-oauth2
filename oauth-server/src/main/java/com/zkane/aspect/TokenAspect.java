package com.zkane.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
    public void doAfterReturning(Object obj) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        System.out.println(obj);
    }
}
