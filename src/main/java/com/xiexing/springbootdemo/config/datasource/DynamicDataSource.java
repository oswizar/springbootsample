package com.xiexing.springbootdemo.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 路由数据源选择
 * 1.如果DataSourceKey存在,根据指定的DataSourceKey实现数据源切换
 * 2.如果DataSourceKey不存在,则自动使用默认数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
