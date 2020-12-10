package com.half.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 15:56
 * @Version 1.0
 * @Description 测试自定义拦截器的使用
 */
@Controller
@RequestMapping("/interceptor")
public class InteceptorController {

    @GetMapping("/test1")
    public String sayHello() {
        System.out.println("hello interceptor!");
        return "success";
    }

    @GetMapping("/test2")
    public String sayHello2() {
        System.out.println("hello interceptor method2!");
        return "success";
    }
}
