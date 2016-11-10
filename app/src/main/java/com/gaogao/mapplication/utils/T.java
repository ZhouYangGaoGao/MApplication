package com.gaogao.mapplication.utils;

import android.widget.Toast;

import com.gaogao.mapplication.MApplication;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/11/8 下午8:45
 * 用途 :吐司工具类
 */
public class T {
    public static void L(Object msg) {
        Toast.makeText(MApplication.getApplication(), msg + "", Toast.LENGTH_LONG).show();
    }

    public static void S(Object msg) {
        Toast.makeText(MApplication.getApplication(), msg + "", Toast.LENGTH_SHORT).show();
    }
}
