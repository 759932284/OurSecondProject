package com.lanou.yindongge.music.pineapple.net;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 17/2/24.
 */

public class PicassoManager extends ImageManager {
    private PicassoManager() {

    }
    private static PicassoManager picassoManager;
    public static PicassoManager getPicassoManager() {
        if (picassoManager == null) {
            picassoManager = new PicassoManager();
        }
        return picassoManager;
    }
    @Override
    public void loadImageView(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }
}
