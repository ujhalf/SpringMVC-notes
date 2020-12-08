package com.half.controller;

import com.half.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/9 1:27
 * @Version 1.0
 * @Description 演示mvc中与请求相应相关的示例
 */
@Controller
@RequestMapping("/response")
public class ResponseController {

    /*演示请求处理方法返回值为String类型，
    这种场景下会根据返回的字符串去依据springmvc.xml中
    的视图解析器配置去匹配相应的视图*/
    @GetMapping("/string")
    public String retStr(Model model) {
        System.out.println("方法返回值为String类型");
        //创建一个对象 使用Model存入视图中 并在页面上展示
        User user = new User();
        user.setAge(15);
        user.setUname("kobe");
        model.addAttribute("user", user);
        //视图的逻辑名称 会返回这个视图
        return "response";
    }
}
