package com.xiexing.springbootdemo.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 拦截@TargetDataSource
 * 1.当存在@TargetDataSource时,则执行该class,获取里面的value,作为指定的数据源
 * 2.当没有@TargetDataSource时,则不执行该class,使用默认数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(targetDataSource)")
    public void beforeSwitchDS(JoinPoint point, TargetDataSource targetDataSource) {
        String dataSourceKey = null;
        try {
            dataSourceKey = targetDataSource.value();
            if (!DataSourceContextHolder.containsDataSource(dataSourceKey)) {
                log.error("DataSource [{}] NotFound! Used Default DataSource >>> {}", targetDataSource.value(), point.getSignature());
            } else {
                log.info("Used DataSource : {} >>> {}", targetDataSource.value(), point.getSignature());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSourceKey);
    }

    @After("@annotation(targetDataSource)")
    public void afterSwitchDS(JoinPoint point, TargetDataSource targetDataSource) {

        log.info("Revert DataSource : {} >>> {}", targetDataSource.value(), point.getSignature());

        //方法执行完毕之后,销毁当前数据源信息,进行垃圾回收
        DataSourceContextHolder.clearDB();
    }
}
