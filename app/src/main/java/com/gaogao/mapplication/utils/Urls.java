package com.gaogao.mapplication.utils;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/29 上午11:36
 * 用途 :所有接口地址
 */
public class Urls {
    public static final String AUTH = "3cc188db6cf90b44e3634557170bfda9";
    private static final String IP = "http://115.29.108.243";//票务
    public static final String TIP2 = "http://210.14.158.158:8585";
    public static String TICKETLIST = IP + "/api3/index_list";
    public static String SHIPIN = TIP2 + "/info/foucs/homePage";


    //首页：
    public static String HOME = "http://dailyapi.ibaozou.com/api/v31/documents/latest";
    //视频：
    public static String VIDIO = "http://dailyapi.ibaozou.com/api/v31/documents/videos/latest";
    //阅读
    public static String READ = "http://dailyapi.ibaozou.com/api/v31/rank/read/week";
    //赞
    public static String VOTE = "http://dailyapi.ibaozou.com/api/v31/rank/vote/week";
    //评论
    public static String COMMENT = "http://dailyapi.ibaozou.com/api/v31/rank/comment/week";
    //栏目
    public static String COLUMN = "http://dailyapi.ibaozou.com/api/v31/channels/index?per_page=10&";
    //搜索
    public static String SEARCH = "http://dailyapi.ibaozou.com/api/v31/documents/search";

}
