package com.half.controller;

import com.half.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/9 1:27
 * @Version 1.0
 * @Description 演示mvc中与请求相应相关的示例
 */
@Controller
@RequestMapping("/response")
public class ResponseController {

    /**
     * 演示请求处理方法返回值为String类型，
     * 这种场景下会根据返回的字符串去依据springmvc.xml中
     * 的视图解析器配置去匹配相应的视图
     */
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


    /**
     * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
     */
    @GetMapping("/void")
    public void retVoid(Model model) throws ServletException, IOException {
        System.out.println("方法返回值为Void类型");
        //创建一个对象 使用Model存入视图中 并在页面上展示
        User user = new User();
        user.setAge(15);
        user.setUname("kobe");
        model.addAttribute("user", user);
    }

    /**
     * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
     * 这时如果不想跳转 可以调用ServletAPI指明处理逻辑 自己调用API进行转发时 并不使用配置的视图解析器 因此路径/文件后缀需要写全
     */
    @GetMapping("/voidForward")
    public void retVoidForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("方法返回值为Void类型，使用原生ServletAPI进行转发处理");
        //使用ServletAPI自行处理后续逻辑
        //使用转发 路径写法与重定向有区别  自己调用转发方法时，并不再使用配置的视图解析器对象，因此路径需要自己配置
        //转发可以直接请求WEB/INF下的内容
        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
        //后续方法不再继续
        return;
    }

    /**
     * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
     * 这时如果不想跳转 可以调用ServletAPI指明处理逻辑 自己调用API进行转发时 并不使用配置的视图解析器 因此路径/文件后缀需要写全
     */
    @GetMapping("/voidRedirect")
    public void retVoidRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("方法返回值为Void类型,使用重定向");
        //创建一个对象 使用Model存入视图中 并在页面上展示
        //使用ServletAPI自己进行后续逻辑的处理
        //使用重定向 路径写法与重定向有区别
        //重定向是两次请求不能直接请求WEB/INF下的内容
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        //后续方法不再继续
        return;
    }

    /**
     * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
     * 使用response调用输出流直接进行处理
     */
    @GetMapping("/voidWriter")
    public void retVoidResponse(HttpServletResponse response) throws ServletException, IOException {
        System.out.println("方法返回值为Void类型,使用response输出流直接响应");
        //设置编码 防止中文乱码
        response.setCharacterEncoding("UTF-8");
        //设置请求头
        response.setContentType("text/html;charset=UTF-8");
        //调用输出流 写出响应内容
        response.getWriter().print("Hi this is a message sent from response.getWriter().print()");
        return;
    }

    /**
     * 返回值为ModelAndView类型
     */
    @GetMapping("modelAndView")
    public ModelAndView retModelAndView() {
        ModelAndView mv = new ModelAndView();
        //添加属性
        User user = new User();
        user.setAge(15);
        user.setUname("kobe");
        mv.addObject("user", user);
        //设置视图名称
        mv.setViewName("success");
        return mv;
    }

    /**
     * 使用forward关键字进行转发
     */
    @GetMapping("/forward")
    public String retForward() {
        System.out.println("使用forward关键字进行转发……");
        return "forward:/WEB-INF/pages/success.jsp";
    }

    /**
     * 使用redirect关键字进行转发
     */
    @GetMapping("/redirect")
    public String retRedirect() {
        System.out.println("使用redirect关键字进行重定向……");
        //此处不加项目名称
        return "redirect:/index.jsp";
    }

    /**
     * 发送ajxa请求，将请求中的数据存入javabean
     * 需要引入jackson进行类型转换
     */

    @PostMapping("/ajax")
    public @ResponseBody User testAjax(@RequestBody User user) {
        System.out.println("测试ajax请求……");
        System.out.println(user);
        //响应
        user.setUname("lebron");
        user.setAge(45);
        return user;
    }
}
