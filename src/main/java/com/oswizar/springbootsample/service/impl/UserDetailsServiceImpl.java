package com.oswizar.springbootsample.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oswizar.springbootsample.model.LoginUser;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User one = userMapper.selectOne(queryWrapper);
        if (null == one) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> authorities = new ArrayList<>();
        if (one.getUsername().equals("root")) {
            authorities.add("admin");
        }
        return new LoginUser(one, authorities);
    }
}
