package com.oswizar.springbootsample.service;

import com.oswizar.springbootsample.entity.ResponseResult;
import com.oswizar.springbootsample.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
