package com.codefriday.controller;

import com.codefriday.pojo.User;
import com.codefriday.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping("/login")
    public String login(String userCode, String userPassword, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = userService.getLoginUser(userCode);
        if(loginUser.getUserPassword().equals(userPassword)){
            session.setAttribute("userSession",loginUser);
        }else{
            session.setAttribute("error","用户名或密码错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }
        return "frame";
    }

    @RequestMapping("/sys/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("userSession");
        request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
    }
}
