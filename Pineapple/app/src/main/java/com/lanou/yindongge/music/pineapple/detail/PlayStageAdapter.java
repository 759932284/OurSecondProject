package com.lanou.yindongge.music.pineapple.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/3/9.
 */

public class PlayStageAdapter extends RecyclerView.Adapter<PlayStageAdapter.PlayStageViewHolder> {
    private List<String> data;
    private Context context;

    public PlayStageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public PlayStageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_play_stage, null);
        PlayStageViewHolder holder = new PlayStageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayStageViewHolder holder, int position) {
        holder.detailPlayStageTv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class PlayStageViewHolder extends RecyclerView.ViewHolder{

        TextView detailPlayStageTv;
        public PlayStageViewHolder(View itemView) {
            super(itemView);
            detailPlayStageTv = (TextView)itemView.findViewById(R.id.detail_play_stage_tv);
        }
    }
}
