package com.lll.service;

import com.lll.domain.ResponseResult;
import com.lll.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);
    ResponseResult logout();
}
