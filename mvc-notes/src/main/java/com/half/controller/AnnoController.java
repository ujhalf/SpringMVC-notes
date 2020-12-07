package com.half.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 21:53
 * @Version 1.0
 * @Description
 */
@RequestMapping("/anno")
@Controller
public class AnnoController {

    /*测试自定义类型转换器
    * spring内置的类型转换器可以将 1994/09/24这种形式的日期进行转换
    * 而无法转换如:1994-09-24这种形式的日期 这时需要自定义类型转换器
    *
    * */
    @PostMapping("customConverter")
    public String testConverter(Date date) {
        System.out.println(date);
        return "success";
    }
}
