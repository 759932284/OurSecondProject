package com.lanou.yindongge.music.pineapple.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.yindongge.music.pineapple.R;

/**
 * Created by dllo on 17/2/24.
 */

public class GlideManager extends ImageManager {
    // 单例
    // 构造方法私有化
    private GlideManager() {

    }

    // 定义私有静态类变量
    private static GlideManager glideManager;
    // 定义对外的静态获取方法
    public static GlideManager getGlideManager() {
        if (null == glideManager) {
            glideManager = new GlideManager();
        }
        return glideManager;
    }


    @Override
    public void loadImageView(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.mipmap.default_album_playing_new)
                .error(R.mipmap.default_album_playing_new).into(imageView);
    }

    public void loadGifImageView(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asGif()
                .error(R.mipmap.default_album_playing_new).into(imageView);
        }
}
