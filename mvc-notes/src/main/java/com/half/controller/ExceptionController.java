package com.half.controller;

import com.half.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 10:07
 * @Version 1.0
 * @Description 演示SpringMVC异常处理
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/test")
    public String testExceptionHandler() throws SysException {
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("参数异常");
        }

        return "success";
    }
}
