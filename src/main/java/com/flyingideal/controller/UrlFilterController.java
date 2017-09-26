package com.flyingideal.controller;

import com.flyingideal.model.UrlFilter;
import com.flyingideal.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanchao
 * @date 2017/9/26 9:35
 */
@RestController
@RequestMapping(value = "/urlFilter")
public class UrlFilterController {

    @Autowired
    private UrlFilterService urlFilterService;

    @GetMapping(value = "/view")
    public Object getUrlFilters() {
        return urlFilterService.getUrlFilters();
    }

    @PutMapping(value = "/add")
    public Object addUrlFilter(@RequestBody UrlFilter urlFilter) {
        return urlFilterService.addUrlFilter(urlFilter);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Object deleteUrlFilter(@PathVariable Long id) {
        return urlFilterService.deleteUrlFilterById(id);
    }
}
