package com.lanou.yindongge.music.pineapple.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.bean.HomeGameTalkResponse;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeCommentFragment extends BaseFragment implements OnNetResultListener{

    private String url;
    private String channel;
    private RecyclerView commentRv;
    private HomeCommentAdapter homeCommentAdapter;
    private SwipeRefreshLayout refresh;
    private int REFRESH = 1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_comment;
    }

    public static HomeCommentFragment newInstance(String url, String channelStr) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("channel", channelStr);
        HomeCommentFragment fragment = new HomeCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        commentRv = (RecyclerView)view.findViewById(R.id.comment_rv);
        refresh = (SwipeRefreshLayout)view.findViewById(R.id.comment_refresh);
    }

    @Override
    public void initData() {
        // 区分频道
        Bundle bundle = getArguments();
        if (bundle != null){
            url = bundle.getString("url");
            channel = bundle.getString("channel");
        }
        homeCommentAdapter = new HomeCommentAdapter(context);
        commentRv.setAdapter(homeCommentAdapter);
        commentRv.setLayoutManager(new LinearLayoutManager(context));
        //    "搞笑", "动画", "萌宠", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说"
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_GAME_TALK_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_LAUGH_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_CATOON_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_ANIMAL_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_FOOD_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_QUADRATIC_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_AMUSE_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_NET_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_HERO_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_LEGEND_REQUESTCODE, this);

        // 下拉刷新
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(REFRESH, 2000);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == REFRESH) {
                initData();
                homeCommentAdapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        }
    };

    @Override
    public void onSuccessListener(String result, int requestCode) {
        Gson gson = new Gson();

        if (requestCode == Contant.HOME_GAME_TALK_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_LAUGH_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_CATOON_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_ANIMAL_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_FOOD_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_QUADRATIC_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_AMUSE_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_NET_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_HERO_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_LEGEND_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
    }
    @Override
    public void onFailureListener(String errMsg) {
        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }
}
