package com.flyingideal.dao;

import com.flyingideal.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/3.
 */
@Repository
public interface UserMapper {

    /**
     * 获取用户总数
     * @return
     */
    int getCount();

    /**
     * 获取所有用户信息
     * @return
     */
    List<User> getAllUser();
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

    /**
     * 根据用户名获取其所有角色
     * @param username
     * @return
     */
    Set<String> getRoles(String username);

    /**
     * 根据用户名获取其所有权限
     * @param username
     * @return
     */
    Set<String> getPermissions(String username);
}
