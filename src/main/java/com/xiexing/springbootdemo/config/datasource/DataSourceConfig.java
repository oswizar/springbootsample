package com.xiexing.springbootdemo.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置程序初始化所需的所有数据源
 */
@Configuration
public class DataSourceConfig {

    //MYSQL
    @Bean(name = "MYSQL")
    @ConfigurationProperties(prefix = "spring.datasource.mysql") // application.properteis中对应属性的前缀
    public DataSource dataSourceMYSQL() {
        return DataSourceBuilder.create().build();
    }

    //ORACLE
    @Bean(name = "ORACLE")
    @ConfigurationProperties(prefix = "spring.datasource.oracle") // application.properteis中对应属性的前缀
    public DataSource dataSourceORACLE() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        // 实例化动态数据源对象
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMYSQL());

        /**
         * 组装数据源集合
         * 1.将每个数据源都加入数据源集合
         * 2.将每个数据源的标识dataSourceKey都加入dataSourceKeys
         */
        Map<Object, Object> dsMap = new HashMap();

        dsMap.put("MYSQL", dataSourceMYSQL());
        DataSourceContextHolder.dataSourceKeys.add("MYSQL");

        dsMap.put("ORACLE", dataSourceORACLE());
        DataSourceContextHolder.dataSourceKeys.add("ORACLE");

        //...

        // 将所有的数据源都放入数据源集
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}