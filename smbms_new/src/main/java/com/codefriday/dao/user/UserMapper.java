package com.codefriday.dao.user;


import com.codefriday.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserMapper {
    //得到登录用户
    public User getLoginUser(@Param("userCode") String userCode) throws Exception;

    //修改用户密码
    public int updatePwd(@Param("id") Integer id,@Param("newPwd") String newPwd) throws Exception;

    //查询用户总数,带特定的参数
    public int getUserCount(@Param("userName") String userName,@Param("userRole") Integer userRole) throws Exception;

    //获得用户列表
    public List<User> getUserList(@Param("userName") String userName, @Param("userRole") Integer userRole,
                                  @Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize) throws Exception;

    //添加用户
    public int addUser(User user) throws Exception;

    //删除用户
    public int delUser(@Param("userID") Integer userID) throws Exception;

    //通过id获得用户
    public User getUserById(@Param("userId") Integer userId) throws Exception;

    //修改用户
    public int modify(User user)throws Exception;
}
