package com.half.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 10:11
 * @Version 1.0
 * @Description 异常处理器
 */
@Component
public class SystemExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SysException e = null;
        if (ex instanceof SysException) {
            e = (SysException) ex;
        } else {
            e = new SysException("系统正在维护");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", e.getMsg());
        mv.setViewName("error");
        return mv;
    }
}
