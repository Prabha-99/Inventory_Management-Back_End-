package com.example.monara_backend.dto;

public class navBarLogin {

    private String firstname;
    private String authority;

    public navBarLogin(String firstname, String authority) {
        this.firstname = firstname;
        this.authority = authority;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
