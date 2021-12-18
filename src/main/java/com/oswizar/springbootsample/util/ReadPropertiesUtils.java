/**
 * 软件著作权：长安新生（深圳）金融投资有限公司
 * 系统名称：马达贷
 */
package com.oswizar.springbootsample.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadPropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReadPropertiesUtils.class);

    /**
     * 读取外挂配置文件
     *
     * @param propertiesFilename 需要读取的配置文件名
     * @param property           所选配置文件中对应的配置项
     * @param vm                 配置的启动参数选项的名字(如-DCONF_HOME=D:\IntelliJ\caxs-cfs\cfs-conf,则vm="CONF_HOME")
     * @return property对应的配置信息
     */
    public static String readPropertiesFromfiles(String propertiesFilename, String property, String vm) {
        // 系统属性
        Properties props = System.getProperties();
        String confHome = props.getProperty(vm);
        logger.info(vm + ":" + confHome);
        Properties prop = new Properties();
        InputStream in = null;
        InputStreamReader reader = null;
        try {
            in = new FileInputStream(confHome + propertiesFilename);
            reader = new InputStreamReader(in, "UTF-8");
            prop.load(reader);
            String value = prop.getProperty(property);
            logger.info(value);
            in.close();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
