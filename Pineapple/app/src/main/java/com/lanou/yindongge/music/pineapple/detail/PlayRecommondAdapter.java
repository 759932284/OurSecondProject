package com.lanou.yindongge.music.pineapple.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/3/9.
 */

public class PlayRecommondAdapter extends RecyclerView.Adapter<PlayRecommondAdapter.PlayRecommondViewHolder> {

    private Context context;
    private List<String> datas;

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public PlayRecommondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PlayRecommondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_play_more_recommond, null);
        PlayRecommondViewHolder holder = new PlayRecommondViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayRecommondViewHolder holder, int position) {
        holder.moreRecommondIv.setImageResource(R.mipmap.ic_launcher);
        holder.moreRecommondTitle.setText(datas.get(position));
        holder.moreRecommondAuthor.setText(datas.get(position));
        holder.moreRecommondNum.setText(1000 + "");
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class PlayRecommondViewHolder extends RecyclerView.ViewHolder{
        ImageView moreRecommondIv;
        TextView moreRecommondTitle;
        TextView moreRecommondAuthor;
        TextView moreRecommondNum;
        public PlayRecommondViewHolder(View itemView) {
            super(itemView);
            moreRecommondIv = (ImageView)itemView.findViewById(R.id.detail_play_more_recommond_iv);
            moreRecommondTitle = (TextView)itemView.findViewById(R.id.detail_play_more_recommond_title_tv);
            moreRecommondAuthor = (TextView)itemView.findViewById(R.id.detail_play_more_recommond_author_tv);
            moreRecommondNum = (TextView)itemView.findViewById(R.id.detail_play_more_recommond_number_tv);
        }
    }
}
