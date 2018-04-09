package com.bici.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: keluosi@bicitech.cn
 * @Date: 2018/4/8 15:26
 * @version: 1.0
 */
@Component
public class BiciUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "{noop}123456", AuthorityUtils.commaSeparatedStringToAuthorityList("USER,ADMIN"));
    }
}
