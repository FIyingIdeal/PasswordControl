package com.flyingideal.dao;

import com.flyingideal.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
@Repository
public interface UserMapper {

    int getCount();
    List<User> getAllUser();
    /**
     * add user
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * find user by username
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    User getUserByUserId(int userId);
}
