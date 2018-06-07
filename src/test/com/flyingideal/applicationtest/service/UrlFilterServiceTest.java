package com.flyingideal.applicationtest.service;

import com.flyingideal.model.UrlFilter;
import com.flyingideal.service.UrlFilterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yanchao
 * @date 2017/9/26 10:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UrlFilterServiceTest {

    @Autowired
    private UrlFilterService urlFilterService;

    @Test
    public void getUrlFiltersTest() {
        System.out.println(urlFilterService.getUrlFilters());
    }

    @Test
    public void addUrlFilterTest() {
        UrlFilter urlFilter = new UrlFilter("动态权限管理", "/urlFilter", "/admin", null);
        Assert.assertTrue(urlFilterService.addUrlFilter(urlFilter));
    }

    @Test
    public void deleteUrlFilterByIdTest() {
        Long id = 4L;
        Assert.assertTrue(urlFilterService.deleteUrlFilterById(id));
    }
}
