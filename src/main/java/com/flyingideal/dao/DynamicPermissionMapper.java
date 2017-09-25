package com.flyingideal.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DynamicPermissionMapper {

    List<Map<String, String>> getUrlFilter();
}
