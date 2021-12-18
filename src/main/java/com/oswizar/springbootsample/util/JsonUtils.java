package com.oswizar.springbootsample.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static SerializeConfig mapping = new SerializeConfig();

    private static ObjectMapper objectMapper;

    static {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    }

    /**
     * @param obj 对象模型
     * @return String 转换完毕的字符串
     * @title: obj2Json
     * @description : 将对象转换为JSON字符串
     */
    public static String obj2Json(Object obj) {
        String str = null;
        try {
            str = JSON.toJSONString(obj, mapping, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return str;
    }

    /**
     * @param json  需要转换的字符串
     * @param clazz 转换时使用的实体类型
     * @param <T>
     * @return Object
     * @title: json2Obj
     * @description : 将JSON字符串转换为对象
     */
    public static <T> T json2Obj(String json, Class<T> clazz) {
        T obj = null;
        try {
            obj = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * @Author: xiaobing
     * @Description: map 转 json
     * @Date: 2018/6/13
     */
    public static String mapToJson(Map<String, Object> map) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * @Author: xiaobing
     * @Description: 判断字符串是否可以转化为json对象
     * @Date: 2018/11/28
     */
    public static boolean isJsonObject(String content) {
        if(StringUtils.isBlank(content)){
            return false;
        }
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    import com.alibaba.fastjson.JSON;

    public static void appendStruct(StringBuilder json,String structName,String value,boolean hasNext){
        json.append("\"");
        json.append(structName);
        json.append("\":");
        json.append(value);

        if(hasNext){
            json.append(",");
        } else {
            json.append("}");
        }
    }


    public static void appendField(StringBuilder json,String fieldName,String value,boolean hasNext){
        json.append("\"");
        json.append(fieldName);
        json.append("\":\"");
        json.append(value);
        json.append("\"");
        if(hasNext){
            json.append(",");
        } else {
            json.append("}");
        }
    }


}
