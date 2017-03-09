package com.lanou.yindongge.music.pineapple.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 17/2/27.
 */

public class ScreenSizeUtils {
    public static int getSreen(Context context, ScreenState state){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(ScreenState.WIDTH)) {
            return metrics.widthPixels;
        } else {
            return metrics.heightPixels;
        }
    }
}
