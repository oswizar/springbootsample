package com.oswizar.springbootsample.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 * @date 2020-4-16
 */
public class DataModel implements Map, Serializable {
    private Logger logger = LoggerFactory.getLogger(DataModel.class);

    private Map fieldMap;

    public Map getMap() {
        return this.fieldMap;
    }

    public DataModel() {
        fieldMap = new HashMap();
    }

    public boolean hasField(String name) {
        return fieldMap.containsKey(name);
    }

    public Object getFieldValue(String name) {
        return fieldMap.get(name);
    }

    public Object setFieldValue(String name, Object value) {
        return put(name, value, false, false);
    }

    public boolean hasFieldValue(String name) {
        return hasField(name) && getFieldValue(name) != null;
    }


    public void setStringValue(String name, String value) {
        setFieldValue(name, value);
    }


    public void setDateValue(String name, Date value) {
        setFieldValue(name, value);
    }

    /***************************************************************************
     * 以下方法，实现Map接口中方法
     *
     **************************************************************************/

    public void clear() {
        fieldMap.clear();
    }

    public boolean containsKey(Object key) {
        return fieldMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return fieldMap.containsValue(value);
    }

    public Set entrySet() {
        return fieldMap.entrySet();
    }

    public Object get(Object key) {
        // if (!fieldMap.containsKey(key)) {
        // 使用规范的java字段名称来获取
        // key = getJavaFieldNameFormatter().format(String.valueOf(key));
        // }
        return fieldMap.get(key);
    }

    public boolean isEmpty() {
        return fieldMap.isEmpty();
    }

    public Set keySet() {
        return fieldMap.keySet();
    }

    public Object put(Object key, Object value, boolean needTranslateValue, boolean needFormatFieldName) {
        Object resultObject = value;
        // if (needTranslateValue) {
        // resultObject = translateValue(value);
        // }
        // 使用规范的java字段名称来存储
        String name = String.valueOf(key);
        // if (needFormatFieldName) {
        // name = getJavaFieldNameFormatter().format(String.valueOf(key));
        // }

        if (value != null) {
            try {
                if ("java.sql.Timestamp".equals(value.getClass().getName())) {
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String datestr = sdf.format(value);
                    resultObject = datestr;
                }
            } catch (Exception e) {
                logger.error("RMCP_framework", "DataModel.put", e);
            }
        }

        fieldMap.put(name, resultObject);

        return resultObject;
    }

    public Object put(Object key, Object value) {
        return put(key, value, true, true);
    }


    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(getMap());
        return buf.toString();
    }

    /**
     * return field name list of the datamodel instance <p> Description : </p>
     *
     * @param @return @throws
     */
    public List getFieldNameList() {
        List fieldNameList = new ArrayList();
        Iterator fieldIter = this.getMap().entrySet().iterator();
        while (fieldIter.hasNext()) {
            fieldNameList.add(((Entry) fieldIter.next()).getKey());
        }
        return fieldNameList;
    }

    public List getFieldNameListNew(String[] headers) {
        List fieldNameList = new ArrayList();
        for (String cloumn : headers) {
            fieldNameList.add(cloumn);
        }
        return fieldNameList;
    }

    public void putAll(Map t) {
        fieldMap.putAll(t);
    }

    public Object remove(Object key) {
        return fieldMap.remove(key);
    }

    public int size() {
        return fieldMap.size();
    }

    public Collection values() {
        return fieldMap.values();
    }


}
