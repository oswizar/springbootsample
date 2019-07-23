package com.xiexing.springbootdemo.dao;

import com.xiexing.springbootdemo.entity.AppAdvice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    Map queryWfiAppAdviceHistory();

}