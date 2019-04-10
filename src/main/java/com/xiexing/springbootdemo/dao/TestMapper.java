package com.xiexing.springbootdemo.dao;

import com.xiexing.springbootdemo.entity.AppAdvice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {


    /**
     * @Description :查看审批轨迹
     * @Author : Wuhengzhen
     * @date : 2017/11/6 11:14
     */

    List<AppAdvice> queryWfiAppAdviceHistory(Map<String, Object> param);


}