package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.AlbumResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondAlbumAdapter extends RecyclerView.Adapter<HomeRecommondAlbumAdapter.HomeRecommondAlbumViewHolder> {

    private Context context;
    private List<AlbumResponse.VideoListBean> dataAlbum;

    public void setDataAlbum(List<AlbumResponse.VideoListBean> dataAlbum) {
        this.dataAlbum = dataAlbum;
        notifyDataSetChanged();
    }

    public HomeRecommondAlbumAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_album_detail, null);
        HomeRecommondAlbumViewHolder holder = new HomeRecommondAlbumViewHolder(view);

        // 适配屏幕宽高
        ViewGroup.LayoutParams lpTitle = holder.albumTitleTv.getLayoutParams();
        lpTitle.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
        holder.albumTitleTv.setLayoutParams(lpTitle);

        // 适配屏幕宽高
        ViewGroup.LayoutParams lp = holder.albumIv.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
        holder.albumIv.setLayoutParams(lp);

        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondAlbumViewHolder holder, int position) {
        holder.albumTitleTv.setText(dataAlbum.get(position).getTitle());
        holder.albumAuthorTv.setText(dataAlbum.get(position).getChannelName());
        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataAlbum.get(position).getCover(), holder.albumIv);
    }

    @Override
    public int getItemCount() {
        return dataAlbum != null ? dataAlbum.size() : 0;
    }

    class HomeRecommondAlbumViewHolder extends RecyclerView.ViewHolder{
        ImageView albumIv;
        TextView albumTitleTv;
        TextView albumAuthorTv;
        public HomeRecommondAlbumViewHolder(View itemView) {
            super(itemView);
            albumIv = (ImageView)itemView.findViewById(R.id.recommond_album_detail_iv);
            albumTitleTv = (TextView)itemView.findViewById(R.id.recommond_album_detail_title_tv);
            albumAuthorTv = (TextView)itemView.findViewById(R.id.recommond_album_detail_author_tv);
        }
    }
}
