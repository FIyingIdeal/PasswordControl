package com.flyingideal.security.filter;

import com.flyingideal.security.AjaxExceptionMessage;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yanchao
 * @date 2017/9/21 16:10
 */
public class AjaxRolesAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(new AjaxExceptionMessage(false, 401, "Your permission is not enough").toJson());
        return false;
    }
}
