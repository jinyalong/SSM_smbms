package com.codefriday.controller;

import com.alibaba.fastjson.JSON;
import com.codefriday.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class RoleController{
    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleService roleService;
    @GetMapping("/roles")
    @ResponseBody
    public String getRoleList(){
        return JSON.toJSONString(roleService.getRoleList());
    }
}
