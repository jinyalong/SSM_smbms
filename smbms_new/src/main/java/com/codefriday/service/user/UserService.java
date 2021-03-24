package com.codefriday.service.user;

import com.codefriday.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //得到登录用户
    public User getLoginUser(String userCode);

    //修改用户密码
    public int updatePwd(Integer id,String newPwd);

    //查询用户总数,带特定的参数
    public int getUserCount(String userName,Integer userRole);

    //获得用户列表
    public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize);

    //添加用户
    public int addUser(User user);

    //删除用户
    public int delUser(Integer userID);

    //通过id获得用户
    public User getUserById(Integer userId);

    //修改用户
    public int modify(User user);
}
