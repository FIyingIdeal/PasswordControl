package com.flyingideal.security.realm;

import com.flyingideal.model.UserModel;
import com.flyingideal.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/27.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        UserModel user = userService.findUserByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();
        }
        /*if (Boolean.TRUE.equals(user.getLocked)) {
            throw new LockedAccountException();
        }*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                getName()
        );
    }

    //权限设置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
