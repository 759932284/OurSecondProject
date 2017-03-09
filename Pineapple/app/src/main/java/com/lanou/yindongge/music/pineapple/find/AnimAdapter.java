package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.net.GlideManager;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.AnimImageHolder> {
    private Context context;

    private List<ZoneListBean.VideoSetListBean> animData;

    public AnimAdapter(Context context) {
        this.context = context;
    }

    public void setAnimData(List<ZoneListBean.VideoSetListBean> animData) {
        this.animData = animData;
        notifyDataSetChanged();
    }


    @Override
    public AnimImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip_image, null);
        AnimImageHolder holder = new AnimImageHolder(itemView);
        ViewGroup.LayoutParams lp = holder.animIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 4;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.animIv.setLayoutParams(lp);
        return holder;
    }

    @Override
    public void onBindViewHolder(AnimImageHolder holder, int position) {
        holder.animTv.setText(animData.get(position).getName());
        GlideManager.getGlideManager().loadImageView(context, animData.get(position).getCover(), holder.animIv);
    }

    @Override
    public int getItemCount() {
        return animData != null ? animData.size() : 0;
    }


    class AnimImageHolder extends RecyclerView.ViewHolder {
        ImageView animIv;
        TextView animTv;

        public AnimImageHolder(View itemView) {
            super(itemView);
            animIv = (ImageView) itemView.findViewById(R.id.gossip_image_image);
            animTv = (TextView) itemView.findViewById(R.id.gossip_image_text);
        }
    }
}
