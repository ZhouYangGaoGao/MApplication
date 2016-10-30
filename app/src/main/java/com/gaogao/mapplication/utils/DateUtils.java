package com.gaogao.mapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    /**
     * @param time yyyy/MM/dd
     * @return
     */
    public static String formatTime(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "yyyy-MM-dd HH:mm"
     * @return
     */
    public static String formatTimes(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String formatAllTimes(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "yyyy-MM-dd"
     * @return
     */
    public static String formatY_M_D(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String formatNOmsTimes(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "HH:mm"
     * @return
     */
    public static String formatHour(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取服务器所需时间戳
     */
    public static String getTimeStamp() {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            sbf.append(1 + (int) (Math.random() * 10));
        }

        long times = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date curDate = new Date(times);// 获取当前时间
        return formatter.format(curDate) + sbf.toString();
    }

    ;

    /**
     * 获取当前时间戳
     */
    public static Long getCurrentTimeStamp() {
        long times = System.currentTimeMillis();
        return times;
    }

    ;

    /**
     * 判断时间戳的差是否小于24小时
     */
//    public static boolean ismorethan24h(long times) {
//        if (times >86400000){
//            //大于24小时
//            return true;
//        }
//         else {
//            return false;
//        }
//    };

    /**
     * @param time
     * @param "yyyy.MM.dd"
     */
    public static String formatDotDate(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
            format = formater.format(new Date(time * 1000));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "mm:ss" 获取剩余时间
     */
    public static String formatMmSs(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("mm:ss");
            format = formater.format(new Date(time * 1000 - System.currentTimeMillis()));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "mm:ss" 获取时间
     */
    public static String getMmSs(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("mm:ss");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "MM/dd" 获取时间
     */
    public static String getMMDD(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("MM/dd");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time
     * @param "HH" 获取时间
     */
    public static String getHH(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("HH");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param time
     * @param "yyyy" 获取时间
     */
    public static String getYyyy(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }/**
     * @param time
     * @param "dd" 获取时间
     */
    public static String getDd(Long time) {
        String format;
        try {
            SimpleDateFormat formater = new SimpleDateFormat("dd");
            format = formater.format(new Date(time));
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
