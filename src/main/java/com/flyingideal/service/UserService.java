package com.flyingideal.service;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    public int addUser(User user) {
        passwordHelper.encryptPassword(user);
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(LocalDateTime.now());
        return userMapper.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User getUserPublicInfoByUsername(String username) {
        return userMapper.getUserPublicInfoByUsername(username);
    }

    /*public User login(UserModel userModel) {
        User dbUserModel = userMapper.getUserByUsername(userModel.getUserName());
        if (dbUserModel != null && isPasswordValid(dbUserModel, userModel.getPassword())) {
            return dbUserModel;
        }
        return null;
    }

    public UserModel checkUserCredentials(String username, String password) {
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);
        return this.login(userModel);
    }

    private boolean isPasswordValid(UserModel dbUserModel, String password) {
        byte[] passwordSalt = Base64.decodeBase64(dbUserModel.getPasswordSalt());
        byte[] passwordHash = Base64.decodeBase64(dbUserModel.getPassword());

        if (passwordSalt == null || passwordHash == null) {
            return false;
        }
        return UserPasswordUtil.validatePassword(password, passwordSalt, passwordHash);
    }*/
}
