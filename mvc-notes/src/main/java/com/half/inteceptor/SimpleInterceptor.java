package com.half.inteceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 15:50
 * @Version 1.0
 * @Description
 */

public class SimpleInterceptor implements HandlerInterceptor {
    //进入controller之前对请求进行处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器前处理方法执行了……");
        //如果使用false进行拦截 可以使用servletAPI进行转发或者重定向
//        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
//        return false;
        return true;
    }

    //从controller方法执行之后进行后处理
    //后处理之后也可以使用ServletAPI进行转发和重定向
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器后处理方法执行了……");
    }

    //最终执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("最终执行的方法……跳转页面后进行的");
    }
}
