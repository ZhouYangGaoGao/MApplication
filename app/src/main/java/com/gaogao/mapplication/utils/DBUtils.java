package com.gaogao.mapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gaogao.mapplication.MApplication;


/**
 *存储
 */
public class DBUtils {
    private static SharedPreferences spf = MApplication.getApplication().getSharedPreferences("AppName", Context.MODE_PRIVATE);

    public static void save(String tag, String value) {
        spf.edit().putString(tag, value);
    }

    public static String get(String tag) {
        return spf.getString(tag, "");
    }

    public static void saveB(String tag, boolean value) {
        spf.edit().putBoolean(tag, value);
    }

    public static boolean getB(String tag) {
        return spf.getBoolean(tag, false);
    }


}
