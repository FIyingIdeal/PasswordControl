package com.flyingideal.service;

import com.flyingideal.dao.AdminMapper;
import com.flyingideal.model.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yanchao
 * @date 2017/9/19 17:02
 */
@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户列表
     * @return
     */
    public List<User> getUsers() {
        return adminMapper.getUsers();
    }


    public boolean addRole(String username, String roleId) {
        User user = userService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            log.error("未找到用户名【{}】的用户", username);
            throw new NullPointerException("未找到用户名【" + username + "】的用户");
        }
        String userId = String.valueOf(user.getId());
        return adminMapper.addRole(userId, roleId, username) > 0;
    }

    public boolean deleteRole(String username, String roleId) {
        return adminMapper.deleteRole(username, roleId) > 0;
    }
}
