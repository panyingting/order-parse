package com.jiao.order.parse.common;

/**
 * 结果枚举
 *
 * @author zhangyinglei1
 * @date 2018/8/7 16:11
 */

public enum ResultEnum {

    SUCCESS(1, "成功"),
    REPEAT(2, "重复数据"),
    REPEAT_IN(3, "重复参与"),
    FAIL(-1, "失败");

    public final int code;

    public final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
