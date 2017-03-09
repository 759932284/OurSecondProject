package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.PopularResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondPopularAdapter extends RecyclerView.Adapter<HomeRecommondPopularAdapter.HomeRecommondPopularViewHolder> {

    private Context context;
    private List<PopularResponse> dataPopular;

    public void setDataPopular(List<PopularResponse> dataPopular) {
        this.dataPopular = dataPopular;
        notifyDataSetChanged();
    }

    public HomeRecommondPopularAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_popular_detail, null);
        HomeRecommondPopularViewHolder holder = new HomeRecommondPopularViewHolder(view);

        // 适配屏幕宽高
        ViewGroup.LayoutParams lp = holder.popularIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 4;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.popularIv.setLayoutParams(lp);

        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondPopularViewHolder holder, int position) {
        holder.popularOrderTv.setText(position + 1 + "");
        holder.popularTitleTv.setText(dataPopular.get(position).getName());
        holder.popularNumTv.setText(dataPopular.get(position).getVideoCount() + "");
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataPopular.get(position).getCover(), holder.popularIv);
    }

    @Override
    public int getItemCount() {
        return dataPopular != null ? dataPopular.size() : 0;
    }

    class HomeRecommondPopularViewHolder extends RecyclerView.ViewHolder{
        ImageView popularIv;
        TextView popularOrderTv;
        TextView popularTitleTv;
        TextView popularNumTv;
        public HomeRecommondPopularViewHolder(View itemView) {
            super(itemView);
            popularIv = (ImageView)itemView.findViewById(R.id.popular_iv);
            popularOrderTv = (TextView)itemView.findViewById(R.id.popular_order_tv);
            popularTitleTv = (TextView)itemView.findViewById(R.id.popular_title_tv);
            popularNumTv = (TextView)itemView.findViewById(R.id.popular_number_tv);
        }
    }
}
