package com.codefriday.service.user;

import com.codefriday.dao.user.UserMapper;
import com.codefriday.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getLoginUser(String userCode) {
        User user = null;
        try {
            user = userMapper.getLoginUser(userCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updatePwd(Integer id, String newPwd) {
        int res = 0;
        try {
            res = userMapper.updatePwd(id,newPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int getUserCount(String userName, Integer userRole) {
        int res = 0;
        try {
            res = userMapper.getUserCount(userName,userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize) {
        List<User> userList = null;
        currentPageNo = (currentPageNo-1)*pageSize;
        try {
            userList = userMapper.getUserList(userName,userRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int addUser(User user) {
        int res = 0;
        try {
            res = userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delUser(Integer userID) {
        int res = 0;
        try {
            res = userMapper.delUser(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = null;
        try {
            user = userMapper.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int modify(User user) {
        int res = 0;
        try {
            res = userMapper.modify(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
