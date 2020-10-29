package com.herman.persistence.test.model;

/**
 * 用户信息
 *
 * @author herman
 * @create 2020-10-28 18:58
 **/

public class UserInfo {


    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
