package com.lanou.yindongge.music.pineapple.find;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class FindFragment extends BaseFragment {

    private RecyclerView rv;
    private FindAdapter findAdapter;

    private List<ZoneListBean> allList;
    private List<ZoneListBean.VideoSetListBean> gossip;
    private List<ZoneListBean.VideoSetListBean> fun;
    private List<ZoneListBean.VideoSetListBean> anim;

    // 下拉刷新
    private SwipeRefreshLayout refresh;
    private static final int REFRESH = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.find_rv);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public void initData() {

        findAdapter = new FindAdapter(context);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(findAdapter);
        // 网络请求-在成功的方法里给RecyclerView设置数据

        OkHttpManager.getInstance().startGetRequest(Url.SEARCH, Contant.FIND_SEARCH_REQUESTCODE, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result, int requestCode) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<SearchBean>>(){}.getType();
                List<SearchBean> searchDatas = gson.fromJson(result, type);
                findAdapter.setSearchDatas(searchDatas);
            }

            @Override
            public void onFailureListener(String errMsg) {
                Log.d("FindAdapter", errMsg + "");
            }
        });

        OkHttpManager.getInstance().startGetRequest(Url.ZONELIST, Contant.FIND_CONTENT_REQUESTCODE, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result, int requestCode) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<ZoneListBean>>(){}.getType();
                allList = gson.fromJson(result, type);
                gossip = allList.get(0).getVideoSetList();
                fun = allList.get(1).getVideoSetList();
                anim = allList.get(2).getVideoSetList();
                findAdapter.setGossip(gossip);
                findAdapter.setFun(fun);
                findAdapter.setAnim(anim);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });


        // 下拉刷新
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(REFRESH, 2000);
            }
        });
        refresh.setColorSchemeColors(Color.RED, Color.GREEN);
    }

    /**
     *  下拉刷新
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == REFRESH) {
                // 重新请求数据
                initData();
                findAdapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        }
    };
}
