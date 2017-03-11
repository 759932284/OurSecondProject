package com.lanou.yindongge.music.pineapple.my.favor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;

import java.util.List;

/**
 * Created by dllo on 17/3/10.
 */

public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.FavorViewHolder> {

    private Context context;
    List<FavorEntity> dataFavor;

    public FavorAdapter(Context context) {
        this.context = context;
    }

    public void setDataFavor(List<FavorEntity> dataFavor) {
        this.dataFavor = dataFavor;
        notifyDataSetChanged();
    }

    @Override
    public FavorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favor, null);
        FavorViewHolder holder = new FavorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FavorViewHolder holder, int position) {
        holder.favorTitleTv.setText(dataFavor.get(position).getTitle());
        holder.favorAuthorTv.setText(dataFavor.get(position).getAuthor());
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataFavor.get(position).getImgStr(), holder.favorIv);
    }

    @Override
    public int getItemCount() {
        return dataFavor != null ? dataFavor.size() : 0;
    }

    class FavorViewHolder extends RecyclerView.ViewHolder{
        ImageView favorIv;
        TextView favorTitleTv;
        TextView favorAuthorTv;
        public FavorViewHolder(View itemView) {
            super(itemView);
            favorIv = (ImageView)itemView.findViewById(R.id.favor_iv);
            favorTitleTv = (TextView)itemView.findViewById(R.id.favor_title_tv);
            favorAuthorTv = (TextView)itemView.findViewById(R.id.favor_author_tv);
        }
    }
}
