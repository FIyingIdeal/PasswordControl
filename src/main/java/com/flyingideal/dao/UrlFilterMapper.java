package com.flyingideal.dao;

import com.flyingideal.model.UrlFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanchao
 * @date 2017/9/26 9:41
 */
@Repository
public interface UrlFilterMapper {

    List<UrlFilter> getUrlFilters();

    int addUrlFilter(UrlFilter urlFilter);

    int deleteUrlFilterById(Long id);
}
