package com.oswizar.springbootsample.config.enumeration;

public enum OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private final String type;

    public String getType() {
        return type;
    }

    OperationType(String type) {
        this.type = type;
    }
}
