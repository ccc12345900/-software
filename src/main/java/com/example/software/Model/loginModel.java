package com.example.software.Model;

import java.sql.Timestamp;

public class loginModel {
    private String username;
    private String password;
    private Timestamp CreateTime;

    public loginModel() {
    }

    public loginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public loginModel(String username, String password, Timestamp createTime) {
        this.username = username;
        this.password = password;
        CreateTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Timestamp createTime) {
        CreateTime = createTime;
    }
}
