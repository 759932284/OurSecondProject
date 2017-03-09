package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.BacteriaResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */

public class HomeRecommondBacteriaAdapter extends RecyclerView.Adapter<HomeRecommondBacteriaAdapter.BacteriaViewHolder> {

    private Context context;
    private List<BacteriaResponse> dataBacteria;

    public void setDataBacteria(List<BacteriaResponse> dataBacteria) {
        this.dataBacteria = dataBacteria;
        notifyDataSetChanged();
    }

    public HomeRecommondBacteriaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BacteriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_bacteria_detail, null);
        BacteriaViewHolder holder = new BacteriaViewHolder(view);

        ViewGroup.LayoutParams lp = holder.bacteriaIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.bacteriaIv.setLayoutParams(lp);

        return holder;
    }

    @Override
    public void onBindViewHolder(BacteriaViewHolder holder, int position) {
        holder.bacteriaTitleTv.setText(dataBacteria.get(position).getTitle());
        holder.bacteriaAuthorTv.setText(dataBacteria.get(position).getChannelName());
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataBacteria.get(position).getCover(), holder.bacteriaIv);
    }

    @Override
    public int getItemCount() {
        return dataBacteria != null ? dataBacteria.size() : 0;
    }

    class BacteriaViewHolder extends RecyclerView.ViewHolder{
        ImageView bacteriaIv;
        TextView bacteriaTitleTv;
        TextView bacteriaAuthorTv;
        public BacteriaViewHolder(View itemView) {
            super(itemView);
            bacteriaIv = (ImageView)itemView.findViewById(R.id.recommond_bacteria_iv);
            bacteriaTitleTv = (TextView)itemView.findViewById(R.id.recommond_bacteria_title_tv);
            bacteriaAuthorTv = (TextView)itemView.findViewById(R.id.recommond_bacteria_anthor_tv);
        }
    }
}
