package com.codefriday.controller;

import com.alibaba.fastjson.JSON;
import com.codefriday.pojo.Role;
import com.codefriday.pojo.User;
import com.codefriday.service.role.RoleService;
import com.codefriday.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class UserController {
    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    RoleService roleService;

    //查询所有，a链接，不带参数
    @GetMapping("/users")
    public String users(Model model){
        return this.query(null,null,null,model);
    }
    //查询，带参数所以使用post
    @PostMapping("/users")
    public String query(String queryname, Integer queryUserRole, Integer pageIndex, Model model){
        //参数处理
        if(queryname=="") queryname=null;
        if (queryUserRole==null) queryUserRole=0;
        if(pageIndex==null) pageIndex=1;
        Integer pageSize = 5;
        //获得用户总数
        int totalCount = userService.getUserCount(queryname,queryUserRole);
        int totalPage = totalCount/pageSize;
        if(totalCount%pageSize!=0) totalPage++;

        if(pageIndex>totalPage) pageIndex = totalPage;
        List<User> userList = userService.getUserList(queryname, queryUserRole, pageIndex, pageSize);
        List<Role> roleList = roleService.getRoleList();

        model.addAttribute("roleList",roleList);
        model.addAttribute("userList",userList);
        model.addAttribute("totalPageCount",totalPage);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("currentPageNo",pageIndex);
        model.addAttribute("queryUserName",queryname);
        model.addAttribute("queryUserRole",queryUserRole);
        return "userlist";
    }

    //跳转到添加界面
    @GetMapping("adduser")
    public String goAdd(){
        return "useradd";
    }

    //跳转到修改密码
    @RequestMapping("pwdmodify")
    public String goPwdModify(){
        return "pwdmodify";
    }

    //异步请求验证旧密码
    @RequestMapping("getold")
    @ResponseBody
    String getOldPwd(String oldpassword, HttpSession session){
        HashMap<String,String> res = new HashMap<String,String>();
        User user = (User) session.getAttribute("userSession");
        if(user!=null){
            if(user.getUserPassword().equals(oldpassword)){
                res.put("result","true");
            }else if(oldpassword==null||oldpassword==""){
                res.put("result","error");
            }else{
                res.put("result","false");
            }
        }else{
            res.put("result","sessionerror");
        }
        return JSON.toJSONString(res);
    }

    //修改密码请求
    @RequestMapping("savepwd")
    void modifyPwd(String rnewpassword, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userSession");
        if(user!=null&&rnewpassword!=null&&rnewpassword!=""){
            int id = user.getId();
            int flag = userService.updatePwd(id, rnewpassword);
            if(flag>0){//修改成功
                request.setAttribute("message","密码修改成功，请重新登录！");
                request.getSession().removeAttribute("userSession");
            }else{
                request.setAttribute("message","修改失败！");
            }
        }else{
            request.setAttribute("message","新密码错误！");
        }
        request.getRequestDispatcher(request.getContextPath()+"/sys/pwdmodify").forward(request,response);

    }

    //验证用户名是否可以添加
    @PostMapping("/user/islegal")
    @ResponseBody
    public String isLegal(String userCode){
        HashMap<String,String> res = new HashMap<String,String>();
        if(userCode==null||userCode==""){
            res.put("result","NotLegal");
            return JSON.toJSONString(res);
        }
        User user = userService.getLoginUser(userCode);

        if(user!=null){
            res.put("result","exist");
        }else {
            res.put("result","notExist");
        }
        return JSON.toJSONString(res);
    }

    //添加用户
    @PostMapping("/user")
    public String addUser(User user,HttpSession session,String method){
        System.out.println("addUser====>"+user);
        if(method!=null&&method.equals("put")){
            user.setModifyBy(((User)session.getAttribute("userSession")).getId());
            user.setModifyDate(new Date());
            userService.modify(user);
        }else{
            user.setCreatedBy(((User)session.getAttribute("userSession")).getId());
            user.setCreationDate(new Date());
            userService.addUser(user);
            return "redirect:/sys/users";
        }
        return "redirect:/sys/users";
    }

    //查看单个用户
    @GetMapping("/user/{uid}")
    public String userView(@PathVariable Integer uid,Model model) {
        model.addAttribute("user", userService.getUserById(uid));
        return "userview";
    }

    @DeleteMapping("/user/{uid}")
    @ResponseBody
    public String deleteUserById(@PathVariable Integer uid){
        HashMap<String,String> res = new HashMap<String,String>();
        if(uid<=0){
            res.put("delResult", "notexist");
        }else{
            if(userService.delUser(uid)>0){
                res.put("delResult", "true");
            }else{
                res.put("delResult", "false");
            }
        }
        return JSON.toJSONString(res);
    }

    @GetMapping("/usermodify/{uid}")
    public String goUpdate(@PathVariable Integer uid,Model model){
        model.addAttribute("user",userService.getUserById(uid));
        return "usermodify";
    }
    @RequestMapping("test")
    @ResponseBody
    public User MyTest(){
        return userService.getUserById(1);
    }

}
