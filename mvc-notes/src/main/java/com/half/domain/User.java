package com.half.domain;

import java.io.Serializable;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 20:37
 * @Version 1.0
 * @Description
 */
public class User implements Serializable {
    private String uname;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
