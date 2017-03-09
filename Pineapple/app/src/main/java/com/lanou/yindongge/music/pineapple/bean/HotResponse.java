package com.lanou.yindongge.music.pineapple.bean;

/**
 * Created by dllo on 17/2/27.
 */

public class HotResponse {

    /**
     * id : 1
     * status : 0
     * code : BORING
     * name : 无聊时间
     * icon : http://bobo-public.nosdn.127.net/bobo_1478673137398_55793782.png
     * background : http://bobo-public.nosdn.127.net/bobo_1478673137573_42302472.jpg
     * staticGraph : http://bobo-public.nosdn.127.net/bobo_1478673137701_16623964.png
     * dynamicGraph : http://bobo-public.nosdn.127.net/bobo_1478673137763_26322616.gif
     * loadingText : http://bobo-public.nosdn.127.net/bobo_1482304069257_64820940.png
     * loadingGraph : http://bobo-public.nosdn.127.net/bobo_1482304069293_45939696.gif
     * beginTime : 21600000
     * endTime : 28800000
     * span : null
     * createTime : 1482304069000
     * isDefault : 0
     */

    private int id;
    private int status;
    private String code;
    private String name;
    private String icon;
    private String background;
    private String staticGraph;
    private String dynamicGraph;
    private String loadingText;
    private String loadingGraph;
    private int beginTime;
    private int endTime;
    private Object span;
    private long createTime;
    private int isDefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getStaticGraph() {
        return staticGraph;
    }

    public void setStaticGraph(String staticGraph) {
        this.staticGraph = staticGraph;
    }

    public String getDynamicGraph() {
        return dynamicGraph;
    }

    public void setDynamicGraph(String dynamicGraph) {
        this.dynamicGraph = dynamicGraph;
    }

    public String getLoadingText() {
        return loadingText;
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    public String getLoadingGraph() {
        return loadingGraph;
    }

    public void setLoadingGraph(String loadingGraph) {
        this.loadingGraph = loadingGraph;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Object getSpan() {
        return span;
    }

    public void setSpan(Object span) {
        this.span = span;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
