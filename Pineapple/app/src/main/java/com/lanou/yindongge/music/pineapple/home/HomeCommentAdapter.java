package com.lanou.yindongge.music.pineapple.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.HomeGameTalkResponse;
import com.lanou.yindongge.music.pineapple.detail.PlayActivity;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class HomeCommentAdapter extends RecyclerView.Adapter<HomeCommentAdapter.HomeCommentFirstViewHolder> {
    private Context context;
    private List<HomeGameTalkResponse> dataHomeGameTalk;
    private HomeCommentDetailAdapter commentDetailAdapter;

    public void setDataHomeGameTalk(List<HomeGameTalkResponse> dataHomeGameTalk) {
        this.dataHomeGameTalk = dataHomeGameTalk;
        notifyDataSetChanged();
    }

    public HomeCommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeCommentFirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_comment_first, null);
        HomeCommentFirstViewHolder holder = new HomeCommentFirstViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeCommentFirstViewHolder holder, final int position) {
        if (dataHomeGameTalk != null && dataHomeGameTalk.size() > 0) {
            View commentHeaderView = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
            //      HomeCommentFirstViewHolder holderFirst = (HomeCommentFirstViewHolder)holder;
            holder.commentFirstTv.setText(dataHomeGameTalk.get(position).getVideoCategory().getName());
            commentDetailAdapter = new HomeCommentDetailAdapter(context, commentHeaderView);
            List<HomeGameTalkResponse.VideoListBean> dataDeatail = dataHomeGameTalk.get(position).getVideoList();
            commentDetailAdapter.setDataDeatail(dataDeatail);
            GridLayoutManager manager = new GridLayoutManager(context, 2);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return commentDetailAdapter.isHeader(position) ? 2 : 1;
                }
            });
            holder.commentFirstRv.setLayoutManager(manager);
            holder.commentFirstRv.setAdapter(commentDetailAdapter);

            /************************ 跳转视频播放详情界面 *********************************/
            commentDetailAdapter.setOnClickCommenListener(new HomeCommentDetailAdapter.OnClickCommenListener() {
                @Override
                public void onClickCommen(int positionDetail) {
                    Intent intent = new Intent(context, PlayActivity.class);
                    String url = dataHomeGameTalk.get(position).getVideoList().get(positionDetail).getLinkMp4();
                    String title = dataHomeGameTalk.get(position).getVideoList().get(positionDetail).getTitle();
                    intent.putExtra("url", url);
                    intent.putExtra("title", title);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataHomeGameTalk != null ? dataHomeGameTalk.size() : 0;
    }

    class HomeCommentFirstViewHolder extends RecyclerView.ViewHolder {
        TextView commentFirstTv;
        RecyclerView commentFirstRv;

        public HomeCommentFirstViewHolder(View itemView) {
            super(itemView);
            commentFirstTv = (TextView) itemView.findViewById(R.id.comment_first_tv);
            commentFirstRv = (RecyclerView) itemView.findViewById(R.id.comment_first_rv);
        }
    }


}


