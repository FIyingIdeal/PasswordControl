package com.flyingideal.service;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.model.User;
import com.flyingideal.utility.UserPasswordUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        Map<String, String> reslut = null;
        byte[] salt = UserPasswordUtil.getSalt();
        byte[] passwordHash = UserPasswordUtil.hashPassword(user.getPassword(), salt);
        user.setPasswordSalt(salt, passwordHash);
        return userMapper.addUser(user);
    }

    public User login(User user) {
        User dbUser = userMapper.findUserByUserName(user.getUserName());
        if (dbUser != null && isPasswordValid(dbUser, user.getPassword())) {
            return dbUser;
        }
        return null;
    }

    private boolean isPasswordValid(User dbUser, String password) {
        byte[] passwordSalt = Base64.decodeBase64(dbUser.getPasswordSalt());
        byte[] passwordHash = Base64.decodeBase64(dbUser.getPassword());

        if (passwordSalt == null || passwordHash == null) {
            return false;
        }
        return UserPasswordUtil.validatePassword(password, passwordSalt, passwordHash);
    }
}
