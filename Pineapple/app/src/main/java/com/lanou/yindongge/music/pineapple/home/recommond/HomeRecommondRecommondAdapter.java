package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.RecommondResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondRecommondAdapter extends RecyclerView.Adapter<HomeRecommondRecommondAdapter.HomeRecommondRecommondViewHolder> {

    private Context context;
    private List<RecommondResponse> dataRecommond;

    public void setDataRecommond(List<RecommondResponse> dataRecommond) {
        this.dataRecommond = dataRecommond;
        notifyDataSetChanged();
    }

    public HomeRecommondRecommondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondRecommondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_recommond_detail, null);
        HomeRecommondRecommondViewHolder holder = new HomeRecommondRecommondViewHolder(view);

        // 适配屏幕宽高
        ViewGroup.LayoutParams lp = holder.recommondIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.recommondIv.setLayoutParams(lp);

        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondRecommondViewHolder holder, int position) {
        holder.recommondTiltleTv.setText(dataRecommond.get(position).getTitle());
        holder.recommondAuthorTv.setText(dataRecommond.get(position).getChannelName());
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataRecommond.get(position).getCover(), holder.recommondIv);
    }

    @Override
    public int getItemCount() {
        return dataRecommond != null ? dataRecommond.size() : 0;
    }

    class HomeRecommondRecommondViewHolder extends RecyclerView.ViewHolder{
        ImageView recommondIv;
        TextView recommondTiltleTv;
        TextView recommondAuthorTv;
        public HomeRecommondRecommondViewHolder(View itemView) {
            super(itemView);
            recommondIv = (ImageView)itemView.findViewById(R.id.recommond_recommond_iv);
            recommondTiltleTv = (TextView)itemView.findViewById(R.id.recommond_recommond_title_tv);
            recommondAuthorTv = (TextView)itemView.findViewById(R.id.recommond_recommond_anthor_tv);
        }
    }
}
