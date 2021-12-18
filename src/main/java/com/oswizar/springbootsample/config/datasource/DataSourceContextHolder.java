package com.oswizar.springbootsample.config.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取并保存需要切换的数据源类型(DataSourceKey)
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 定义所有数据源的dataSourceKeys集合
     */
    public static List<String> dataSourceKeys = new ArrayList<>();

    // 1.先设置数据源名
    public static void setDB(String dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    // 2.再获取数据源名
    public static String getDB() {
        return (CONTEXT_HOLDER.get());
    }

    // 3.最后清除数据源名
    public static void clearDB() {
        CONTEXT_HOLDER.remove();
    }

    public static boolean containsDataSource(String dataSourceKey) {

        return dataSourceKeys.contains(dataSourceKey);

    }
}
