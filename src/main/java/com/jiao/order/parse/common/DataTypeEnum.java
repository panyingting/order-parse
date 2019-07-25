package com.jiao.order.parse.common;

public enum DataTypeEnum {

    NUMBER(1, "数字"),
    STRING(2, "字符串"),
    ;


    private DataTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int code;

    private String name;
}
