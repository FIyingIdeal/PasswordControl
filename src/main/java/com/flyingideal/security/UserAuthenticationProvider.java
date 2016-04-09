package com.flyingideal.security;

import com.flyingideal.model.UserModel;

import com.flyingideal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;


public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("spring security ====" + username + "    " + password);
        if (username == null || password == null) {
            return null;
        }
        UserModel userModel = userService.checkUserCredentials(username, password);
        Authentication result = null;
        if (userModel != null) {
//            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
//            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            result = new UsernamePasswordAuthenticationToken(userModel, password);
        }
        return result;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}