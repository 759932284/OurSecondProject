package com.lanou.yindongge.music.pineapple.hot;

/**
 * Created by dllo on 17/2/25.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.HotResponse;
import com.lanou.yindongge.music.pineapple.detail.PlayActivity;
import com.lanou.yindongge.music.pineapple.hot.CardSlidePanel.CardSwitchListener;
import com.lanou.yindongge.music.pineapple.net.GlideManager;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 卡片Fragment
 *
 * @author xmuSistone
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
public class CardFragment extends Fragment {

    String videoUrl = "";
    String imgUrl = "";



    private CardSwitchListener cardSwitchListener;

    private String imagePaths[] = {"assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg",
            "assets://wall12.jpg", "assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg",
            "assets://wall12.jpg"}; // 24个图片资源名称

    private String names[] = {"郭富城", "刘德华", "张学友", "李连杰", "成龙", "谢霆锋", "李易峰",
            "霍建华", "胡歌", "曾志伟", "吴孟达", "梁朝伟", "周星驰", "赵本山", "郭德纲", "周润发", "邓超",
            "王祖蓝", "王宝强", "黄晓明", "张卫健", "徐峥", "李亚鹏", "郑伊健"}; // 24个人名

    private List<CardDataItem> dataList = new ArrayList<CardDataItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_layout, null);
        initView(rootView);
        return rootView;

    }


    private void initView(View rootView) {
        CardSlidePanel slidePanel = (CardSlidePanel) rootView
                .findViewById(R.id.image_slide_panel);
        cardSwitchListener = new CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.d("CardFragment", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("CardFragment", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
            }

            @Override
            public void onItemClick(View cardView, int index) {
                Log.d("CardFragment", "卡片点击-" + dataList.get(index).userName);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), PlayActivity.class);
                        intent.putExtra("url", videoUrl);
                        Log.d("asd", "123:" + 123);
                        getContext().startActivity(intent);
                    }
                });
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);

        prepareDataList();
        slidePanel.fillData(dataList);
    }

    private void prepareDataList() {

        int num = imagePaths.length;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < num; i++) {
                CardDataItem dataItem = new CardDataItem();
                dataItem.userName = names[i];
                dataItem.imagePath = imagePaths[i];
                dataItem.likeNum = (int) (Math.random() * 10);
                dataItem.imageNum = (int) (Math.random() * 6);
                dataList.add(dataItem);
            }
        }

/*************   加加++++++++++    ************/
        String url = "http://m.live.netease.com/bolo/api/rank/hotVideo.htm?type=LUNCKBREAK&userId=5702015542626208498";
        int requestCode = 0;
        OkHttpManager.getInstance().startGetRequest(url, 0, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result, int requestCode) {
                Gson gson = new Gson();
                List<FoodBean> foodData;
                Type type = new TypeToken<List<FoodBean>>() {}.getType();
                foodData = gson.fromJson(result, type);

                for (int i = 0; i < foodData.size(); i++) {
                    videoUrl = foodData.get(i).getLinkMp4();
                    imgUrl = foodData.get(i).getCover();
                }
//                GlideManager.getGlideManager().loadImageView();
//                Intent intent = new Intent(getContext(), PlayActivity.class);
//                intent.putExtra("url", videoUrl);
//                intent.putExtra("imgUrl", imgUrl);
//                startActivity(intent);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        String url = "http://m.live.netease.com/bolo/api/rank/hotVideo.htm?type=LUNCKBREAK&userId=5702015542626208498";
//        int requestCode = 0;
//        OkHttpManager.getInstance().startGetRequest(url, 0, new OnNetResultListener() {
//            @Override
//            public void onSuccessListener(String result, int requestCode) {
//                Gson gson = new Gson();
//                List<FoodBean> foodData;
//                Type type = new TypeToken<List<FoodBean>>(){}.getType();
//                foodData = gson.fromJson(result, type);
//                FoodBean fb = new FoodBean();
//                fb = foodData.get(0);
//                Log.d("CardFragment", fb.getLinkMp4());
//            }
//
//            @Override
//            public void onFailureListener(String errMsg) {
//
//            }
//        });
//
//    }
    }
}
