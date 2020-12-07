package com.half.controller;

import com.half.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 12:15
 * @Version 1.0
 * @Description 关于请求参数的
 */
@RequestMapping("/param")
@Controller
public class ParamController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello SpringMVC");
        return "success";
    }

    /*测试params属性的使用，在params指明相应的键值对，表示对参数的要求，此处表示user参数是必传参数*/
    @GetMapping(value = "/withParams1", params = {"user"})
    public String withParam1(String user) {
        System.out.println("withParam1" + "执行了");
        System.out.println("参数:'+user+'的值为:" + user);
        return "success";
    }

    /*测试params属性的使用，在params指明相应的键值对，表示对参数的要求，下面这种表示方法表示user参数必须为特定的值*/
    @GetMapping(value = "/withParams2", params = {"user=Kobe"})
    public String withParam2(String user) {
        System.out.println("withParam2" + "执行了");
        System.out.println("参数:'+user+'的值为:" + user);
        return "success";
    }

    /*测试params属性的使用，在params指明相应的键值对，表示对参数的要求，下面这种表示方法表示user参数不能为特定的值*/
    @GetMapping(value = "/withParams3", params = {"user!Kobe"})
    public String withParam3(String user) {
        System.out.println("withParam2" + "执行了");
        System.out.println("参数:'+user+'的值为:" + user);
        return "success";
    }

    /*测试headers属性的使用，请求中必须包含指明的头信息*/
    @GetMapping(value = "/withHeaders", headers = {"Accept!=text/html"})
    public String withHeaders(HttpServletRequest request) {
        System.out.println("withHeaders" + "执行了");
        System.out.println("请求的头信息如下:");
        System.out.println(request.getHeader("Accept"));
        return "success";
    }

    @GetMapping("/bindParam")           //此处形参的名字与请求中参数的名字应当一致
    public String simpleParameterBinding(String name) {
        System.out.println("simpleParameterBinding()方法执行了……");
        System.out.println(name);
        return "success";
    }

    //post请求时会出现中文乱码问题 配置方法:
    @PostMapping("/mutipleParams")           //此处形参的名字与请求中参数的名字应当一致
    public String multipleParamsBinding(Account account) throws UnsupportedEncodingException {
        System.out.println("multipleParamsBinding()方法执行了……");
        System.out.println(account);
        return "success";
    }
}
