package com.oswizar.springbootsample.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TestMapper {

    @MapKey("TIME")
    Map<String, Object> queryWfiAppAdviceHistory();

}
