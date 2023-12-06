package com.oswizar.springbootsample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oswizar.springbootsample.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
