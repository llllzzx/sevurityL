package com.expression;

import com.lll.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
public class LExpressionRoot {

    public boolean hasAuthority(String authority){
//        获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permission = loginUser.getPermission();

//        判断用户权限集合是否存在acutority
        return permission.contains(authority);



    }
}
