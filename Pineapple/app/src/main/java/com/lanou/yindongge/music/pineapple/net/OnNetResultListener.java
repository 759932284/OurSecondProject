package com.lanou.yindongge.music.pineapple.net;

/**
 * Created by dllo on 17/2/24.
 */

public interface OnNetResultListener {
    void onSuccessListener(String result, int requestCode);
    void onFailureListener(String errMsg);
}
