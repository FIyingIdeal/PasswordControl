package com.flyingideal.admintest;

import com.flyingideal.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yanchao
 * @date 2017/9/21 17:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class adminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void addRoleTest() {
        Assert.assertTrue(adminService.addRole("100", "100"));
    }

    @Test
    public void deleteRoleTest() {
        Assert.assertTrue(adminService.deleteRole("100", "100"));
    }
}
