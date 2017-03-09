package com.lanou.yindongge.music.pineapple.home.recommond;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeRecommondFragment extends BaseFragment {

    private RecyclerView recommondRec;
    private HomeRecommondAdapter homeRecommondAdapter;
    private SwipeRefreshLayout refresh;
    private int FRESH = 1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_recommond;
    }

    @Override
    public void initView(View view) {

        recommondRec = byView(R.id.recommond_rec);
        refresh = byView(R.id.refresh);
    }

    @Override
    public void initData() {
        homeRecommondAdapter = new HomeRecommondAdapter(context);
        recommondRec.setAdapter(homeRecommondAdapter);
        recommondRec.setLayoutManager(new LinearLayoutManager(context));

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(FRESH, 2000);
            }
        });
        refresh.setColorSchemeColors(Color.BLACK);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FRESH) {
                initData();
                homeRecommondAdapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        }
    };

}
