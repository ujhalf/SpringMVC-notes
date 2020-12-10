package com.half.exception;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 10:09
 * @Version 1.0
 * @Description 自定义异常对象
 */
public class SysException extends Exception {
    private String message;

    public SysException(String msg) {
        this.message = msg;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}
