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

public class FunAdapter extends RecyclerView.Adapter<FunAdapter.FunImageHolder> {
    private Context context;

    private List<ZoneListBean.VideoSetListBean> funList;

    public FunAdapter(Context context) {
        this.context = context;
    }

    public void setFunList(List<ZoneListBean.VideoSetListBean> funList) {
        this.funList = funList;
        notifyDataSetChanged();
    }

    @Override
    public FunImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip_image, null);
        FunImageHolder holder = new FunImageHolder(itemView);
        ViewGroup.LayoutParams lp = holder.funIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 4;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.funIv.setLayoutParams(lp);
        return holder;
    }

    @Override
    public void onBindViewHolder(FunImageHolder holder, int position) {
        holder.funTv.setText(funList.get(position).getName());
        GlideManager.getGlideManager().loadImageView(context, funList.get(position).getCover(), holder.funIv);
    }

    @Override
    public int getItemCount() {
        return funList != null ? funList.size() : 0;
    }

    class FunImageHolder extends RecyclerView.ViewHolder {
        ImageView funIv;
        TextView funTv;
        public FunImageHolder(View itemView) {
            super(itemView);
            funIv =  (ImageView)itemView.findViewById(R.id.gossip_image_image);
            funTv = (TextView)itemView.findViewById(R.id.gossip_image_text);
        }
    }
}
