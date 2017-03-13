package com.lanou.yindongge.music.pineapple.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.GameTalkMoreResponse;

import java.util.List;

/**
 * Created by dllo on 17/3/9.
 */

/**
 * 视频详情界面-播放集数的适配器
 */
public class PlayStageAdapter extends RecyclerView.Adapter<PlayStageAdapter.PlayStageViewHolder> {
    private List<GameTalkMoreResponse> dataMore;
    private Context context;

    public PlayStageAdapter(Context context) {
        this.context = context;
    }

    public void setDataMore(List<GameTalkMoreResponse> dataMore) {
        this.dataMore = dataMore;
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
        holder.detailPlayStageTv.setText(dataMore.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataMore != null ? dataMore.size() : 0;
    }

    class PlayStageViewHolder extends RecyclerView.ViewHolder{

        TextView detailPlayStageTv;
        public PlayStageViewHolder(View itemView) {
            super(itemView);
            detailPlayStageTv = (TextView)itemView.findViewById(R.id.detail_play_stage_tv);
        }
    }
}
