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

    //MySQL
    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql") // application.properteis中对应属性的前缀
    public DataSource dataSourceOfmysql() {
        return DataSourceBuilder.create().build();
    }

    //ORACLE
    @Bean(name = "oracle")
    @ConfigurationProperties(prefix = "spring.datasource.oracle") // application.properteis中对应属性的前缀
    public DataSource dataSourceOforacle() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // 配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceOfmysql());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put("mysql", dataSourceOfmysql());
        dsMap.put("oracle", dataSourceOforacle());
        //...
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}