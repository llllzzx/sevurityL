package com.lll.controller;

import com.lll.domain.ResponseResult;
import com.lll.domain.User;
import com.lll.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginServcie.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
       return loginServcie.logout() ;

    }
}
