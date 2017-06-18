package com.example.logonrm.persistencia.persistencia.Models;

/**
 * Created by Luiz Aquino on 2017-06-17.
 */

public class Login {
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getCodUser() {
        return codUser;
    }

    public void setCodUser(long codUser) {
        this.codUser = codUser;
    }

    private long codUser;
    private String Username;
    private String Password;
}
