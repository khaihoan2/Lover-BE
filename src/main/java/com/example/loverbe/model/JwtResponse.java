package com.example.loverbe.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private Long id;

    private String name;

    private String type = "Bearer ";

    private String token;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String name, String token, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.token = type + token;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
