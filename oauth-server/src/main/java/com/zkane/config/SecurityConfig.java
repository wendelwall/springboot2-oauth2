package com.zkane.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description:
 * @author: 594781919@qq.com
 * @Date: 2018/4/8 10:46
 * @version: 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*.js",
                        "/**/*.css" ,
                        "/oauth/**",
                        "/login/**",
                        "/logout"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                // 自动登录
                /*.and()
                    .rememberMe()
                    // 加密的秘钥
                    .key("unique-and-secret")
                    // 存放在浏览器端cookie的key
                    .rememberMeCookieName("remember-me-cookie-name")
                    // token失效的时间，单位为秒
                    .tokenValiditySeconds(60 * 60 * 25)*/
                .and()
                // 暂时禁用CSRF，否则无法提交登录表单
                .csrf().disable();

    }

}
