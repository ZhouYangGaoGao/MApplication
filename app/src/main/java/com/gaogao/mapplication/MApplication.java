package com.gaogao.mapplication;

import android.app.Application;

import org.xutils.x;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午8:12
 * 用途 :
 */
public class MApplication extends Application {
    private static MApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        x.Ext.init(this);
    }

    public static MApplication getApplication() {
        return application;
    }
}
