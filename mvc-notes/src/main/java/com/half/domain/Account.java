package com.half.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 20:29
 * @Version 1.0
 * @Description
 */
public class Account implements Serializable {
    private String username;
    private String password;
    private Double money;
    private User user;

    private List<Integer>list;
    private Map<String,Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Integer> getList() {
        return list;
    }


    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", user=" + user +
                ", list=" + list +
                ", map=" + map +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
