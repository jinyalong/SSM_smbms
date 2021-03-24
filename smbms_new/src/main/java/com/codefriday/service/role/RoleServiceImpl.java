package com.codefriday.service.role;

import com.codefriday.dao.role.RoleMapper;
import com.codefriday.pojo.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = null;
        try{
            roleList = roleMapper.getRoleList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleList;
    }
}
