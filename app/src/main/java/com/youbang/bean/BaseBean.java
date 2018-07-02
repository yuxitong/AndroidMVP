package com.youbang.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class BaseBean<T> implements Serializable {
    /**
     * 数据
     */
//    private T body;


    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
