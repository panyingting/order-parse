package com.jiao.order.parse.common;


import java.io.Serializable;

/**
 * 前端封装结果类
 * @author zhangyinglei1
 * @date 2018/8/7 16:08
 */

public class WebResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int code;

    private final String msg;

    private T data;


    private WebResult(ResultEnum resultEnum) {
        this.code = resultEnum.code;
        this.msg = resultEnum.msg;
    }

    private WebResult(ResultEnum resultEnum, String msg) {
        this.code = resultEnum.code;
        this.msg = msg;
    }

    private WebResult(ResultEnum resultEnum, String msg, T data) {
        this.code = resultEnum.code;
        this.msg = msg;
        this.data = data;
    }

    public static WebResult failResult() {
        return new WebResult(ResultEnum.FAIL);
    }

    public static WebResult failResult(ResultEnum resultEnum) {
        return new WebResult<>(resultEnum);
    }

    public static WebResult failResult(String msg) {
        return new WebResult<>(ResultEnum.FAIL, msg);
    }

    public static<T> WebResult<T> failResult(String msg, T date) {
        return new WebResult<>(ResultEnum.FAIL, msg, date);
    }

    public static WebResult sucessResult() {
        return new WebResult(ResultEnum.SUCCESS);
    }

    public static<T> WebResult sucessResult(T date) {
        return new WebResult<>(ResultEnum.SUCCESS, null, date);
    }

    public static<T> WebResult<T> sucessResult(T date, String msg) {
        return new WebResult<>(ResultEnum.SUCCESS, msg, date);
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess(){
        return code == ResultEnum.SUCCESS.code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
