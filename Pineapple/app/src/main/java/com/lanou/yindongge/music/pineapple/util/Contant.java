package com.lanou.yindongge.music.pineapple.util;

/**
 * Created by dllo on 17/2/24.
 */

public class Contant {
    public static final String BASE_URL = "http://m.live.netease.com/bolo/api/";
    // 首页-推荐-菠萝菌力荐
    public static final String BACTERIA = BASE_URL + "index/videoRecommend.htm?pageNum=1";
    // 首页-推荐-人气榜
    public static final String POPULAR = BASE_URL + "index/dailyPopList.htm?pageNum=1&pageSize=9";
    // 首页-推荐-游戏杂谈
    public static final String GAME_TALK = BASE_URL + "index/showZoneList.htm?len=5";
    // 首页-推荐-菠萝专辑
    public static final String ALBUM = BASE_URL + "index/hotVideoAlbum.htm";
    // 首页-推荐-为您推荐
    public static final String RECOMMOND = BASE_URL + "index/personalizedRecommend.htm?pageNum=1&userId=5702015542626208498&pageSize=20";
    // 首页-推荐-轮播图
    public static final String BANNER = BASE_URL + "index/bannerVideoNew.htm";
    // 首页-游戏杂谈
    public static final String HOME_GAME_TALK = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14847213448701";
    // 首页-搞笑
    public static final String HOME_LAUGH = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401831";
    // 首页-动画
    public static final String HOME_CATOON = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401821";
    // 首页-萌宠
    public static final String HOME_ANIMAL = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401781";
    // 首页-美食
    public static final String HOME_FOOD = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401801";
    // 首页-二次元
    public static final String HOME_QUADRATIC = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401851";
    // 首页-娱乐
    public static final String HOME_AMUSE = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401791";
    // 首页-网剧
    public static final String HOME_NET = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14679608401841";
    // 首页-英雄联盟
    public static final String HOME_HERO = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14836951235121";
    // 首页-炉石传说
    public static final String HOME_LEGEND = BASE_URL + "zone/categoryVideoList.htm?len=5&zoneId=14836951235151";


    public static final String HOT_BORING = BASE_URL + "rank/sceneList.htm";

    // 请求码
    private static int index = 0;
    // 首页-推荐-菠萝菌力荐 请求码
    public static final int BACTERIA_REQUESTCODE = index++;
    // 首页-推荐-人气榜
    public static final int POPULAR_REQUESTCODE = index++;
    // 首页-推荐-游戏杂谈
    public static final int GAME_TALK_REQUESTCODE = index++;
    // 首页-推荐-菠萝专辑
    public static final int ALBUM_REQUESTCODE = index++;
    // 首页-推荐-为您推荐
    public static final int RECOMMOND_REQUESTCODE = index++;

    // 发现页 搜索
    public static final int FIND_SEARCH_REQUESTCODE = index++;
    // 发现页内容
    public static final int FIND_CONTENT_REQUESTCODE = index++;

    // 首页-推荐-轮播图
    public static final int BANNER_REQUESTCODE = index++;
    // 首页-游戏杂谈
    public static final int HOME_GAME_TALK_REQUESTCODE = index++;
    // 首页-搞笑
    public static final int HOME_LAUGH_REQUESTCODE = index++;
    // 首页-动画
    public static final int HOME_CATOON_REQUESTCODE = index++;
    // 首页-萌宠
    public static final int HOME_ANIMAL_REQUESTCODE = index++;
    // 首页-美食
    public static final int HOME_FOOD_REQUESTCODE = index++;
    // 首页-二次元
    public static final int HOME_QUADRATIC_REQUESTCODE = index++;
    // 首页-娱乐
    public static final int HOME_AMUSE_REQUESTCODE = index++;
    // 首页-网剧
    public static final int HOME_NET_REQUESTCODE = index++;
    // 首页-英雄联盟
    public static final int HOME_HERO_REQUESTCODE = index++;
    // 首页-炉石传说
    public static final int HOME_LEGEND_REQUESTCODE = index++;

    //
    public static final int HOT = index++;
}
