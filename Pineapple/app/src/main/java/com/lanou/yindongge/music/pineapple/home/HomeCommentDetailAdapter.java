package com.lanou.yindongge.music.pineapple.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.HomeGameTalkResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class HomeCommentDetailAdapter extends RecyclerView.Adapter<HomeCommentDetailAdapter.HomeCommentDetailViewHolder> {

    private Context context;
    private View commentHeaderView;
    private List<HomeGameTalkResponse.VideoListBean> dataDeatail;


    /********************    点击事件    *********************************************/
    private HomeCommentDetailAdapter.OnClickCommenListener onClickCommenListener;

    public void setOnClickCommenListener(HomeCommentDetailAdapter.OnClickCommenListener onClickCommenListener) {
        this.onClickCommenListener = onClickCommenListener;
        notifyDataSetChanged();
    }
    /********************    点击事件    *********************************************/

    public void setDataDeatail(List<HomeGameTalkResponse.VideoListBean> dataDeatail) {
        this.dataDeatail = dataDeatail;
        notifyDataSetChanged();
    }

    public HomeCommentDetailAdapter(Context context, View commentHeaderView) {
        this.context = context;
        this.commentHeaderView = commentHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else return 1;
    }

    @Override
    public HomeCommentDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeCommentDetailViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeCommentDetailViewHolder(commentHeaderView);
                ViewGroup.LayoutParams lpHeader = holder.commentHeaderIv.getLayoutParams();
                lpHeader.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH);
                lpHeader.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 3;
                holder.commentHeaderIv.setLayoutParams(lpHeader);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_game_detail, null);
                holder = new HomeCommentDetailViewHolder(view);
                ViewGroup.LayoutParams lp = holder.commentDetailIv.getLayoutParams();
                lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 21 * 10;
                lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 5;
                holder.commentDetailIv.setLayoutParams(lp);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeCommentDetailViewHolder holder, int position) {
        if (isHeader(position)) {
            holder.commentHeaderTitleTv.setText(dataDeatail.get(position).getTitle());
            holder.commentHeaderAuthorTv.setText(dataDeatail.get(position).getChannelName());
            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                    dataDeatail.get(position).getCover(), holder.commentHeaderIv);



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onClickCommenListener.onClickCommen(pos);
                }
            });


            return;
        }
        holder.commentDetailTitleTv.setText(dataDeatail.get(position).getTitle());
        holder.commentDetailAuthorTv.setText(dataDeatail.get(position).getChannelName());

//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        // 适应屏幕宽高
//        Display display = windowManager.getDefaultDisplay();
//        int screenWidth = display.getWidth();
//        int screenHeight = display.getHeight();
//        Glide.with(context)
//                .load(dataDeatail.get(position - 1).getAvatar())
//                .override(screenWidth / 2, screenHeight / 3)
//                .into(holder.commentDetailIv);

        ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                dataDeatail.get(position - 1).getAvatar(), holder.commentDetailIv);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                onClickCommenListener.onClickCommen(pos);
            }
        });

//                dataDeatail.get(position).getCover(), holder.commentDetailIv);

    }

    @Override
    public int getItemCount() {
        return dataDeatail != null ? dataDeatail.size() : 0;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    class HomeCommentDetailViewHolder extends RecyclerView.ViewHolder{
        TextView commentHeaderTitleTv;
        TextView commentHeaderAuthorTv;
        TextView commentDetailTitleTv;
        TextView commentDetailAuthorTv;
        ImageView commentHeaderIv;
        ImageView commentDetailIv;
        public HomeCommentDetailViewHolder(View itemView) {
            super(itemView);
            commentHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            commentHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            commentDetailTitleTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_title_tv);
            commentDetailAuthorTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_author_tv);
            commentHeaderIv =  (ImageView) itemView.findViewById(R.id.home_recommond_header_iv);
            commentDetailIv =  (ImageView) itemView.findViewById(R.id.recommond_game_detail_iv);
        }
    }

    interface OnClickCommenListener {
        void onClickCommen(int position);
    }
}
