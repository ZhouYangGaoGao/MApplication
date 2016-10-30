package com.gaogao.mapplication.bean;

import java.io.Serializable;

/**
 * Created by zhengzai on 16/8/25.
 */
public class Data implements Serializable {
    int code;
    Msg msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
}
