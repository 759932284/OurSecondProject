package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.GameTalkResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondGameAdapter extends RecyclerView.Adapter<HomeRecommondGameAdapter.HomeRecommondGameViewHolder> {

    private Context context;
    private List<GameTalkResponse.VideoListBean> dataGameTalk;
    private View gameHeaderView;

    public void setDataGameTalk(List<GameTalkResponse.VideoListBean> dataGameTalk) {
        this.dataGameTalk = dataGameTalk;
        notifyDataSetChanged();
    }

    public HomeRecommondGameAdapter(Context context, View gameHeaderView) {
        this.context = context;
        this.gameHeaderView = gameHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else return 1;
    }

    @Override
    public HomeRecommondGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeRecommondGameViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeRecommondGameViewHolder(gameHeaderView);
                // 适配屏幕宽高
                ViewGroup.LayoutParams lpHeader = holder.gameHeaderIv.getLayoutParams();
                lpHeader.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH);
                lpHeader.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 3;
                holder.gameHeaderIv.setLayoutParams(lpHeader);

                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_game_detail, null);
                holder = new HomeRecommondGameViewHolder(view);

                // 适配屏幕宽高
                ViewGroup.LayoutParams lp = holder.gameDetailIv.getLayoutParams();
                lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
                lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
                holder.gameDetailIv.setLayoutParams(lp);

                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondGameViewHolder holder, int position) {

        if (isHeader(position)) {
            holder.gameHeaderTitleTv.setText(dataGameTalk.get(position).getTitle());
            holder.gameHeaderAuthorTv.setText(dataGameTalk.get(position).getChannelName());
            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                    dataGameTalk.get(position).getCover(), holder.gameHeaderIv);
            return;
        }
        holder.gameDetailTitleTv.setText(dataGameTalk.get(position).getTitle());
        holder.gameDetailAuthorTv.setText(dataGameTalk.get(position).getChannelName());
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataGameTalk.get(position).getCover(), holder.gameDetailIv);
    }

    @Override
    public int getItemCount() {
        return dataGameTalk != null ? dataGameTalk.size() : 0 ;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    class HomeRecommondGameViewHolder extends RecyclerView.ViewHolder{
        TextView gameHeaderTitleTv;
        TextView gameHeaderAuthorTv;
        TextView gameDetailTitleTv;
        TextView gameDetailAuthorTv;
        ImageView gameHeaderIv;
        ImageView gameDetailIv;
        public HomeRecommondGameViewHolder(View itemView) {
            super(itemView);
            gameHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            gameHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            gameDetailTitleTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_title_tv);
            gameDetailAuthorTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_author_tv);
            gameHeaderIv =  (ImageView) itemView.findViewById(R.id.home_recommond_header_iv);
            gameDetailIv =  (ImageView) itemView.findViewById(R.id.recommond_game_detail_iv);

        }
    }
}
