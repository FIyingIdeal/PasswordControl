package com.flyingideal.security.filter;

import com.flyingideal.utility.BeanJsonConverter;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final String AJAXREQUESTHEADER = "XMLHttpRequest";

    private static final Logger log = LoggerFactory.getLogger(AjaxFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    /**
     * 登录成功调用该方法
     * 在 onAccessDenied() 方法里会调用executeLogin()方法，登录成功即调用onLoginSuccess()
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!AJAXREQUESTHEADER.equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
            issueSuccessRedirect(request, response);
        } else {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Login Success");
            String result = BeanJsonConverter.bean2Json(responseMap);
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println(result);
            out.flush();
            out.close();
        }
        return false;
    }

    /**
     * 登录失败调用该方法
     * 在 onAccessDenied() 方法里会调用executeLogin()方法，登录失败即调用onLoginFailure()
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (!AJAXREQUESTHEADER.equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))) {
            if (log.isDebugEnabled()) {
                log.debug( "Authentication exception", e );
            }
            setFailureAttribute(request, e);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String exceptionName = e.getClass().getSimpleName();
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", false);
            if (IncorrectCredentialsException.class.getSimpleName().equals(exceptionName)) {
                responseMap.put("message", "用户名/密码错误");
            } else if (UnknownAccountException.class.getSimpleName().equals(exceptionName)) {
                responseMap.put("message", "用户名/密码错误");
            } else if (LockedAccountException.class.getSimpleName().equals(exceptionName)) {
                responseMap.put("message", "账号被锁定");
            } else {
                responseMap.put("message", "没有授权的账号");
            }
            String result = BeanJsonConverter.bean2Json(responseMap);
            out.println(result);
            out.flush();
            out.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
