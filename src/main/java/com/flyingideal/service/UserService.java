package com.flyingideal.service;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 添加用户 OR 用户注册
     * @param user
     * @return
     */
    public int addUser(User user) {
        passwordHelper.encryptPassword(user);
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(LocalDateTime.now());
        return userMapper.addUser(user);
    }

    /**
     * 根据用户名获取用户用户信息
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    /**
     * 根据用户名获取用户公开信息（不包含用户密码及salt信息）
     * @param username
     * @return
     */
    public User getUserPublicInfoByUsername(String username) {
        return userMapper.getUserPublicInfoByUsername(username);
    }

    /**
     * 根据用户名获取用户拥有角色
     * @param username
     * @return
     */
    public Set<String> getRoles(String username) {
        return userMapper.getRoles(username);
    }

    /**
     * 根据用户名获取用户拥有权限
     */
    public Set<String> getPermissions(String username) {
        return userMapper.getPermissions(username);
    }

}
