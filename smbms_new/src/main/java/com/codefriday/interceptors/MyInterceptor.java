package com.codefriday.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("userSession")!=null){
            return true;
        }
        request.getRequestDispatcher(request.getContextPath()+"/error.jsp").forward(request,response);
        return false;
    }
}
