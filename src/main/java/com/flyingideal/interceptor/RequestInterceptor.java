package com.flyingideal.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * 获取查看请求参数HandlerInterceptor
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("{} {} 请求参数列表：", request.getMethod(), request.getRequestURL());
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> logger.info("{} = {}", key, Arrays.asList(value)));

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            logger.info("{} = {}", parameterName, request.getParameter(parameterName));
        }
        return true;
    }
}
