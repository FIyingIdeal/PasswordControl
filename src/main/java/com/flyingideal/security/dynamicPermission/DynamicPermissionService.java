package com.flyingideal.security.dynamicPermission;

import java.util.Map;

/**
 * @author yanchao
 * @date 2017/9/25 14:39
 */
public interface DynamicPermissionService {

    //public static final
    String PERMISSION_PATTERN_STRING = "perms[{0}]";

    String ROLE_PATTERN_STRING = "roles[{0}]";

    String ALL_PATTERN_FILTER_NAME = "anon";

    /**
     * 初始化filterChains（在xml配置文件中配置的）
     */
    void init();

    /**
     * 更新框架资源权限配置，需要线程同步，此方法会动态添加definitions
     * 如果有重复的url，新的map将覆盖以前的map
     * 也就是说，以前链接的权限配置会被新的权限配置覆盖
     * @param newDefinitions
     */
    void updatePermission(Map<String, String> newDefinitions);

    /**
     * 需要线程同步，此方法会加载静态配置，DynamicPermissionDao查询出来的配置
     */
    void reloadPermission();
}
