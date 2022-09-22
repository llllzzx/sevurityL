package com.lll.service.impl;

import com.lll.domain.LoginUser;
import com.lll.domain.ResponseResult;
import com.lll.domain.User;
import com.lll.service.LoginServcie;
import com.lll.utils.JwtUtil;
import com.lll.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginServcie {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)){
            throw new RuntimeException("error");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);

        redisCache.setCacheObject("login"+userId,loginUser);

        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        //        获取SecurityContextHolder中的用户id
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //        删除redis中的值
        redisCache.deleteObject("login:"+userId);


        return new ResponseResult(200,"注销成功");
    }
}
