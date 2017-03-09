package com.lanou.yindongge.music.pineapple.home.gametalk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class HomeGameTalkMobileGameAdapter extends RecyclerView.Adapter<HomeGameTalkMobileGameAdapter.HomeGameTalkMobileGameViewHolder> {
    private Context context;
    private List<String> dataMobileGame;
    private View mobileGameHeaderView;

    public void setDataMobileGame(List<String> dataMobileGame) {
        this.dataMobileGame = dataMobileGame;
        notifyDataSetChanged();
    }

    public HomeGameTalkMobileGameAdapter(Context context, View mobileGameHeaderView) {
        this.context = context;
        this.mobileGameHeaderView = mobileGameHeaderView;
    }

    @Override
    public HomeGameTalkMobileGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeGameTalkMobileGameViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeGameTalkMobileGameViewHolder(mobileGameHeaderView);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_gametalk_mobilegame_detail, null);
                holder = new HomeGameTalkMobileGameViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeGameTalkMobileGameViewHolder holder, int position) {
        if (isHeader(position)) {
            holder.mobileGameHeaderTitleTv.setText(dataMobileGame.get(position));
            holder.mobileGameHeaderAuthorTv.setText(dataMobileGame.get(position));
            //  Log.d("HomeRecommondGameAdapte", dataScoff.get(position));
            return;
        }
        holder.mobileGameDetailTitleTv.setText(dataMobileGame.get(position - 1));
        holder.mobileGameDetailAuthorTv.setText(dataMobileGame.get(position - 1));
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else return 1;
    }

    @Override
    public int getItemCount() {
        return dataMobileGame != null ? dataMobileGame.size() : 0;
    }


    public boolean isHeader(int position) {
        return position == 0;
    }
    class HomeGameTalkMobileGameViewHolder extends RecyclerView.ViewHolder{
        TextView mobileGameHeaderTitleTv;
        TextView mobileGameHeaderAuthorTv;
        TextView mobileGameDetailTitleTv;
        TextView mobileGameDetailAuthorTv;
        public HomeGameTalkMobileGameViewHolder(View itemView) {
            super(itemView);
            mobileGameHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            mobileGameHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            mobileGameDetailTitleTv = (TextView)itemView.findViewById(R.id.gametalk_mobile_game_detail_title_tv);
            mobileGameDetailAuthorTv = (TextView)itemView.findViewById(R.id.gametalk_mobile_game_detail_author_tv);
        }
    }
}
