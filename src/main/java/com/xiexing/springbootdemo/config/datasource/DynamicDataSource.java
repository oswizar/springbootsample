package com.xiexing.springbootdemo.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @date: 2019/4/4 14:00
 * @author: oswizar
 * @description: 根据DataSourceKey实现数据源切换
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
