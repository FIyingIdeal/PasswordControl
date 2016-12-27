package com.flyingideal.service;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.UserModel;
import com.flyingideal.utility.UserPasswordUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(UserModel userModel) {
        byte[] salt = UserPasswordUtil.getSalt();
        byte[] passwordHash = UserPasswordUtil.hashPassword(userModel.getPassword(), salt);
        userModel.setPasswordSalt(salt, passwordHash);
        return userMapper.addUser(userModel);
    }

    public UserModel login(UserModel userModel) {
        UserModel dbUserModel = userMapper.findUserByUsername(userModel.getUserName());
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
    }

    public UserModel findUserByUsername(String userName) {
        return userMapper.findUserByUsername(userName);
    }
}
