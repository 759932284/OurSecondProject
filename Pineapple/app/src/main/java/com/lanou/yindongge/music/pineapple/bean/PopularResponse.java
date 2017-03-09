package com.lanou.yindongge.music.pineapple.bean;

/**
 * Created by dllo on 17/2/24.
 */

public class PopularResponse {

    /**
     * name : 暴暴勺暴暴 大神第一视角
     * cover : http://bobo-public.nosdn.127.net/bobo_1484814598494_37492799.jpg
     * intro :
     * channelName : 暴暴勺暴暴
     * videoCount : 149
     * playCount : 635033
     * degree : 175452
     * userId : 2730482396659594966
     * userIdStr : 2730482396659594966
     * avatar : http://bobo-public.nosdn.127.net/bobo_1484721433889_20518532.jpg
     * sid : 14847309385191
     * videoId : 14871242241971
     */

    private String name;
    private String cover;
    private String intro;
    private String channelName;
    private int videoCount;
    private int playCount;
    private int degree;
    private long userId;
    private String userIdStr;
    private String avatar;
    private long sid;
    private long videoId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }
}
