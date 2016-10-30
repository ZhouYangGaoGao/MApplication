package com.gaogao.mapplication.utils;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午8:17
 * 用途 :
 */
public interface MListener {
    void onSuccess(String tag, String value);

    void onFailure(String tag, String value);

    void onException(String tag, String value);

    void onStart(String tag);

    void onFinish(String tag);

}
