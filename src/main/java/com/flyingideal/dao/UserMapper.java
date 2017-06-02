package com.flyingideal.dao;

import com.flyingideal.model.User;
import com.flyingideal.model.UserModel;
import org.apache.ibatis.annotations.Param;
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
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * get all user information(include password and salt) by username
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 通过username获取用户的公开信息（不包括用户的密码和salt）
     * @param username
     * @return
     */
    User getUserPublicInfoByUsername(String username);

    /**
     * 通过userId获取用户所有信息（包括密码和salt）
     * @param userId
     * @return
     */
    User getUserByUserId(int userId);

    List<UserModel> getPasswordTest(@Param("username")String username,
                                    @Param("orderby") String orderby);
}
