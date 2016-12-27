package com.flyingideal.dao;

import com.flyingideal.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
@Repository
public interface UserMapper {

    int getCount();
    List<UserModel> getAllUser();
    /**
     * add user
     * @param userModel
     * @return
     */
    int addUser(UserModel userModel);

    /**
     * find user by username
     * @param userName
     * @return
     */
    UserModel findUserByUsername(String username);

    UserModel getUserByUserId(int userId);
}
