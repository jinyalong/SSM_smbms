package com.codefriday.dao.role;

import com.codefriday.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleMapper {

    //获得角色列表
    public List<Role> getRoleList() throws Exception;
}
