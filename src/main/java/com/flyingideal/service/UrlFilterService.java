package com.flyingideal.service;

import com.flyingideal.dao.UrlFilterMapper;
import com.flyingideal.model.UrlFilter;
import com.flyingideal.security.dynamicPermission.DynamicPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanchao
 * @date 2017/9/26 9:40
 */
@Service
public class UrlFilterService {

    @Autowired
    private DynamicPermissionService dynamicPermissionService;

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    public List<UrlFilter> getUrlFilters() {
        return urlFilterMapper.getUrlFilters();
    }

    public boolean addUrlFilter(UrlFilter urlFilter) {
        boolean result = urlFilterMapper.addUrlFilter(urlFilter) > 0;
        if (result) {
            dynamicPermissionService.reloadPermission();
        }
        return result;
    }

    public boolean deleteUrlFilterById(Long id) {
        boolean result = urlFilterMapper.deleteUrlFilterById(id) > 0;
        if (result) {
            dynamicPermissionService.reloadPermission();
        }
        return result;
    }
}
