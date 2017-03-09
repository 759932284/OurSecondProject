package com.lanou.yindongge.music.pineapple.home.laugh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class HomeLaughScoffAdapter extends RecyclerView.Adapter<HomeLaughScoffAdapter.HomeLaughScoffViewHolder> {
    private Context context;
    private View viewHeader;
    private List<String> dataScoff;

    public void setDataScoff(List<String> dataScoff) {
        this.dataScoff = dataScoff;
        notifyDataSetChanged();
    }

    public HomeLaughScoffAdapter(Context context, View viewHeader) {
        this.context = context;
        this.viewHeader = viewHeader;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public boolean isHeader(int position) {
        return position == 0;
    }
    @Override
    public HomeLaughScoffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeLaughScoffViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeLaughScoffViewHolder(viewHeader);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_laugh_scoff_detail, null);
                holder = new HomeLaughScoffViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeLaughScoffViewHolder holder, int position) {
        if (isHeader(position)) {
            holder.scoffHeaderTitleTv.setText(dataScoff.get(position));
            holder.scoffHeaderAuthorTv.setText(dataScoff.get(position));
            return;
        }
        holder.scoffDetailTitleTv.setText(dataScoff.get(position));
        holder.scoffDetailAuthorTv.setText(dataScoff.get(position));
    }

    @Override
    public int getItemCount() {
        return dataScoff != null ? dataScoff.size() : 0;
    }

    class HomeLaughScoffViewHolder extends RecyclerView.ViewHolder{
        TextView scoffHeaderTitleTv;
        TextView scoffHeaderAuthorTv;
        TextView scoffDetailTitleTv;
        TextView scoffDetailAuthorTv;
        public HomeLaughScoffViewHolder(View itemView) {
            super(itemView);
            scoffHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            scoffHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            scoffDetailTitleTv = (TextView)itemView.findViewById(R.id.laugh_scoff_detail_title_tv);
            scoffDetailAuthorTv = (TextView)itemView.findViewById(R.id.laugh_scoff_detail_author_tv);
        }
    }
}
