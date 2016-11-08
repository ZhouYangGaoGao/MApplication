package com.gaogao.mapplication.utils;

import android.util.Log;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/29 上午11:58
 * 用途 :
 */
public class L {
    private static boolean isDebug = true;

    public static void i(String msg) {
        if (isDebug)
            Log.i("=========>", msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e("=========>", msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d("=========>", msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w("=========>", msg);
    }
}
