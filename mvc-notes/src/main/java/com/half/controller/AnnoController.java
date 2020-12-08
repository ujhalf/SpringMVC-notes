package com.half.controller;

import com.half.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 21:53
 * @Version 1.0
 * @Description SpringMVC常用注解的使用
 */
@RequestMapping("/anno")
@Controller
@SessionAttributes(value = {"msg"}) //将这个属性存入session域 用于参数共享
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

    /*原生servletAPI的使用*/
    @GetMapping("/servletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(request.getSession());
        System.out.println(request.getSession().getServletContext());
        System.out.println(response);
        return "success";
    }

    /*@RequestParam注解*/
    @GetMapping("/requestParam")
    public String testRequestParam(@RequestParam("name") String username) {
        System.out.println(username);
        return "success";
    }

    /*@RequestBody注解*/
    @PostMapping("/requestBody")
    public String testRequestBody(@RequestBody String user) {
        System.out.println(user);
        return "success";
    }

    /*PathVariable注解*/
    @GetMapping("/path/{uid}")
    public String testPathVariable(@PathVariable("uid") String id) {
        System.out.println(id);
        return "success";
    }

    /*测试使用RequestHeader注解*/
    @GetMapping("/requestHeader")
    public String testHttpHeader(@RequestHeader("Accept") String header) {
        System.out.println(header);
        return "success";
    }

    /**
     * CookieValue注解
     **/
    @GetMapping("/cookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
        return "success";
    }

//    /*@ModelAttribute注解配合使用 用于补充某些未填入的字段*/
//    @PostMapping("/user")
//    public String testModelAttribute(User user) {
//        System.out.println(user);
//        return "success";
//    }
//
//    @ModelAttribute
//    public User method(String uname) {
//        User user = new User();
//        user.setUname(uname);
//        user.setAge(100);
//
//        System.out.println("ModelAttribute注解的方法执行了……");
//        return user;
//    }

    /*@ModelAttribute注解配合使用 用于补充某些未填入的字段*/
    @PostMapping("/user")
    public String testModelAttribute(@ModelAttribute("abc") User user) {
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public void method(String uname, Map<String, User> map) {
        User user = new User();
        user.setUname(uname);
        user.setAge(100);
        map.put("abc", user);
        System.out.println("ModelAttribute注解的方法执行了……");
    }


    /*SessionAttributes*/
    @GetMapping("/sessionAttributes")
    public String testSessionAttributes(Model model) {
        //向model中填入属性会存入request对象中
        model.addAttribute("msg", "美美");
        return "success";
    }

    /*SessionAttributes*/
    @GetMapping("/getSessionAttribute")
    public String getSessionAttributes(ModelMap model) {
        //从session中读取属性
        Object msg = model.get("msg");
        System.out.println(msg);
        return "success";
    }


    /*SessionAttributes*/
    @GetMapping("/deleteSessionAttribute")
    public String deleteSessionAttribute(SessionStatus status) {
        //删除session中的属性
        status.setComplete();
        return "success";
    }

}
