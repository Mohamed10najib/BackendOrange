package com.example.demo.Dto;

import java.util.List;

public class AuthenticationResponse {
    private String token;
    private String firstname;
    private String lastname;

    private List<String> roles;

    // Constructor


    public AuthenticationResponse(String token, String firstname, String lastname, List<String> roles) {
        this.token = token;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

