package com.flyingideal.controller;

import com.flyingideal.model.User;
import com.flyingideal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yanchao
 * @date 2017/9/19 16:57
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 获取所有用户列表
     * @return
     */
    @GetMapping(value = "/listuser")
    public List<User> getUsers() {
        //TODO 当用户量比较大的时候需要做分页处理
        return adminService.getUsers();
    }


    @PutMapping(value = "/addrole/{username}/{roleId}")
    public Object addRole(@PathVariable String username,
                           @PathVariable String roleId) {
        return adminService.addRole(username, roleId);
    }

    @DeleteMapping(value = "/delete/{username}/{roleId}")
    public Object deleteRole(@PathVariable String username,
                             @PathVariable String roleId) {
        return adminService.deleteRole(username, roleId);
    }
}
