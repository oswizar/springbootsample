package com.oswizar.springbootsample.mapper;

import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface TestMapper {

    @MapKey("TIME")
    Map<String, Object> queryWfiAppAdviceHistory();

}
