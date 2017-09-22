package com.flyingideal.dao;

import com.flyingideal.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanchao
 * @date 2017/9/19 17:02
 */
@Repository
public interface AdminMapper {

    /**
     * 获取用户列表
     * @return
     */
    List<User> getUsers();

    int addRole(@Param("userId") String userId,
                @Param("roleId") String roleId,
                @Param("username") String username);

    int deleteRole(@Param("username") String username,
                   @Param("roleId") String roleId);
}
