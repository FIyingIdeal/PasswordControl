package com.flyingideal.security.dynamicPermission;

import com.flyingideal.dao.DynamicPermissionMapper;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanchao
 * @date 2017/9/25 14:58
 */
public class DynamicPermissionServiceImpl implements DynamicPermissionService {

    private static final Logger log = LoggerFactory.getLogger(DynamicPermissionServiceImpl.class);

    private String permissionPatternString = PERMISSION_PATTERN_STRING;
    private String rolePatternString = ROLE_PATTERN_STRING;
    private String allPatternFilterName = ALL_PATTERN_FILTER_NAME;
    private boolean addDefaultAllPatternFilter = true;

    /**
     * {@code ShiroFilterFactoryBean}实现了{@code FactoryBean}接口，而{@link ShiroFilterFactoryBean#getObject()}方法的
     * 返回值类型就是{@code AbstractShiroFilter}，因此可以获取到一个该实例
     */
    private AbstractShiroFilter shiroFilter;

    private String definitions = "";

    @Autowired
    private DynamicPermissionMapper mapper;

    @Override
    @PostConstruct
    public synchronized void init() {
        log.info("权限加载开始...");
        reloadPermission();
        log.info("权限加载完成...");
    }

    @Override
    public synchronized void reloadPermission() {
        Map<String, String> filterChainMap = generateFilterChainDefinitionMap();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) getFilterChainManager();
        manager.getFilterChains().clear();
        addToChain(manager, filterChainMap);
    }

    /**
     * 更新路径权限
     * @param newDefinitions 参数形式：key值为对应的url，value值为拦截器链的字符串定义，如 authc,roles[admin],permission[create]
     */
    @Override
    public synchronized void updatePermission(Map<String, String> newDefinitions) {
        log.info("权限更新开始...");
        DefaultFilterChainManager manager = (DefaultFilterChainManager) getFilterChainManager();
        addToChain(manager, newDefinitions);
        log.info("权限更新完成");
    }

    private void addToChain(DefaultFilterChainManager manager, Map<String, String> filterChainMap) {
        NamedFilterList allPatternFilterList = manager.getFilterChains().remove("/**");
        filterChainMap.forEach((k, v) -> {
            manager.createChain(k, v);
        });

        //添加  /**  的拦截器链
        if (!CollectionUtils.isEmpty(allPatternFilterList)) {
            manager.getFilterChains().put("/**", allPatternFilterList);
        } else if (addDefaultAllPatternFilter) {
            manager.createChain("/**", allPatternFilterName);
        }
    }

    /**
     * 获取静态及动态的FilterChain定义
     * @return 返回的Map结构key值为对应的url，value值为拦截器链的字符串定义，如 authc,roles[admin],permission[create]
     */
    private Map<String, String> generateFilterChainDefinitionMap() {
        //加载静态的FilterChain定义（在xml配置文件中配置好的）
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }

        //从数据库获取动态的FilterChain定义
        Map<String, String> dynamicFilterChainMap = loadDynamicFilterChainMap();
        if (!CollectionUtils.isEmpty(dynamicFilterChainMap)) {
            section.putAll(dynamicFilterChainMap);
        }
        return section;
    }

    private Map<String, String> loadDynamicFilterChainMap() {
        List<Map<String, String>> dynamicFilterChainMapList = mapper.getUrlFilter();
        Map<String, String> dynamicFilterChainMap = new LinkedHashMap<>();
        dynamicFilterChainMapList.forEach((item) -> {
            String chainMapString = getFilterChainMapString(item);
            if (StringUtils.hasLength(chainMapString)) {
                dynamicFilterChainMap.put(item.get("url"), chainMapString);
            }
        });
        return dynamicFilterChainMap;
    }

    private FilterChainManager getFilterChainManager() {
        PathMatchingFilterChainResolver filterChainResolver =
                (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        return filterChainResolver.getFilterChainManager();
    }

    private String getFilterChainMapString(Map<String, String> chainMap) {
        StringBuilder builder = new StringBuilder();
        String roles = chainMap.get("roles");
        String perms = chainMap.get("perms");
        //hasText()在只有空格的情况下将返回false
        if (StringUtils.hasText(roles)) {
            builder.append(rolePatternString.replace("{0}", roles.trim().replace(" ", "")));
        }
        if (StringUtils.hasText(perms)) {
            String permissionString = permissionPatternString.replace("{0}", perms.trim().replace(" ", ""));
            if (builder.length() > 0 && StringUtils.hasLength(permissionString)) {
                builder.append(",").append(permissionString);
            }
        }
        return builder.toString();
    }

    public AbstractShiroFilter getShiroFilter() {
        return shiroFilter;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    public String getDefinitions() {
        return definitions;
    }

    public void setDefinitions(String definitions) {
        this.definitions = definitions;
    }

    public DynamicPermissionMapper getMapper() {
        return mapper;
    }

    public void setMapper(DynamicPermissionMapper mapper) {
        this.mapper = mapper;
    }

    public String getPermissionPatternString() {
        return permissionPatternString;
    }

    public void setPermissionPatternString(String permissionPatternString) {
        this.permissionPatternString = permissionPatternString;
    }

    public String getRolePatternString() {
        return rolePatternString;
    }

    public void setRolePatternString(String rolePatternString) {
        this.rolePatternString = rolePatternString;
    }

    public String getAllPatternFilterName() {
        return allPatternFilterName;
    }

    public void setAllPatternFilterName(String allPatternFilterName) {
        this.allPatternFilterName = allPatternFilterName;
    }

    public boolean isAddDefaultAllPatternFilter() {
        return addDefaultAllPatternFilter;
    }

    public void setAddDefaultAllPatternFilter(boolean addDefaultAllPatternFilter) {
        this.addDefaultAllPatternFilter = addDefaultAllPatternFilter;
    }
}
