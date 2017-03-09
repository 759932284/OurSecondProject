package com.lanou.yindongge.music.pineapple.home.gametalk;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeGameTalkAdapter extends RecyclerView.Adapter {
    private Context context;

    /******************** **************************************/
//    private OnVideoPlayListener onVideoPlayListener;
//
//    public void setOnVideoPlayListener(OnVideoPlayListener onVideoPlayListener) {
//        this.onVideoPlayListener = onVideoPlayListener;
//    }
    /******************** **************************************/

    public HomeGameTalkAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View viewScoff = LayoutInflater.from(context).inflate(R.layout.item_home_gametalk_scoff, null);
                holder = new HomeGameTalkScoffViewHolder(viewScoff);
                break;
            case 1:
                View viewMobileGame = LayoutInflater.from(context).inflate(R.layout.item_home_gametalk_mobilegame, null);
                holder = new HomeGameTalkMobileGameViewHolder(viewMobileGame);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                // 抽取跨列的布局
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeGameTalkScoffViewHolder holderScoff = (HomeGameTalkScoffViewHolder)holder;
                holderScoff.scoffTv.setText("游戏吐槽");
                // 构建嵌套的适配器
                final HomeGameTalkScoffAdapter scoffAdapter = new HomeGameTalkScoffAdapter(context, view);
                List<String> dataScoff =  new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dataScoff.add("题目");
                }
                scoffAdapter.setDataScoff(dataScoff);
                // 网格布局设置两列
                GridLayoutManager scoffManager = new GridLayoutManager(context, 2);
                // 布局管理者设置跨列
                scoffManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return scoffAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                // 设置布局类型,连接适配器
                holderScoff.scoffRv.setLayoutManager(scoffManager);
                holderScoff.scoffRv.setAdapter(scoffAdapter);

                break;
            case 1:
                // 抽取跨列的布局
                view = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeGameTalkMobileGameViewHolder holderMobileGame = (HomeGameTalkMobileGameViewHolder)holder;
                holderMobileGame.mobileGameTv.setText("单机手游");
                // 构建嵌套的适配器
                final HomeGameTalkMobileGameAdapter mobileGameAdapter = new HomeGameTalkMobileGameAdapter(context, view);
                List<String> dataMobileGame =  new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dataMobileGame.add("题目");
                }
                mobileGameAdapter.setDataMobileGame(dataMobileGame);
                // 网格布局设置两列
                GridLayoutManager mobileGameManager = new GridLayoutManager(context, 2);
                // 布局管理者设置跨列
                mobileGameManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return mobileGameAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                // 设置布局类型,连接适配器
                holderMobileGame.mobileGameRv.setLayoutManager(mobileGameManager);
                holderMobileGame.mobileGameRv.setAdapter(mobileGameAdapter);


                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class HomeGameTalkScoffViewHolder extends RecyclerView.ViewHolder {
        TextView scoffTv;
        RecyclerView scoffRv;
        public HomeGameTalkScoffViewHolder(View itemView) {
            super(itemView);
            scoffTv = (TextView) itemView.findViewById(R.id.game_talk_scoff_tv);
            scoffRv = (RecyclerView) itemView.findViewById(R.id.scoff_rv);
        }
    }
    class HomeGameTalkMobileGameViewHolder extends RecyclerView.ViewHolder {
        TextView mobileGameTv;
        RecyclerView mobileGameRv;
        public HomeGameTalkMobileGameViewHolder(View itemView) {
            super(itemView);
            mobileGameTv = (TextView) itemView.findViewById(R.id.game_talk_mobile_game_tv);
            mobileGameRv = (RecyclerView) itemView.findViewById(R.id.mobile_game_rv);
        }
    }


    /******************** **************************************/
//    interface OnVideoPlayListener{
//        void onClick(int position);
//    }
    /******************** **************************************/
}
