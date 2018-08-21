package com.zkane.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: 594781919@qq.com
 * @date: 2018/4/9
 */
public class MyGrantedAuthority implements GrantedAuthority {
    private String roleId;
    private String menuId;

    @Override
    public String getAuthority() {
        return roleId + "&" + menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public MyGrantedAuthority(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
