package com.lanou.yindongge.music.pineapple.net;

/**
 * Created by dllo on 17/2/24.
 */

/**
 * 图片加载工厂
 */
public class ImageManagerFactory {
    public static final String GLIDE = "glide";
    public static final String PICASSO = "picasso";
    public static ImageManager getImageManager(String type) {
        if (type.equals(GLIDE)) {
            return GlideManager.getGlideManager();
        }
        else if (type.equals(PICASSO)) {
            return PicassoManager.getPicassoManager();
        }
        else return null;
    }
}
