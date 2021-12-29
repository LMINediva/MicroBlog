package com.lc.model;

import java.io.Serializable;

/**
 * @author DELL
 * @date 2021/12/29 18:28
 */
public class Account implements Serializable {
    private String name;
    private String password;
    private String email;

    public Account() {
    }

    public Account(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
