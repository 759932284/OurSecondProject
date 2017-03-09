package com.lanou.yindongge.music.pineapple.home.gametalk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeGameTalkScoffAdapter extends RecyclerView.Adapter<HomeGameTalkScoffAdapter.HomeGameTalkScoffViewHolder> {
    private Context context;
    private List<String> dataScoff;
    private View scoffHeaderView;



    public void setDataScoff(List<String> dataScoff) {
        this.dataScoff = dataScoff;
        notifyDataSetChanged();
    }

    public HomeGameTalkScoffAdapter(Context context, View scoffHeaderView) {
        this.context = context;
        this.scoffHeaderView = scoffHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else return 1;
    }

    @Override
    public HomeGameTalkScoffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeGameTalkScoffViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeGameTalkScoffViewHolder(scoffHeaderView);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_gametalk_scoff_detail, null);
                holder = new HomeGameTalkScoffViewHolder(view);

                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeGameTalkScoffViewHolder holder, int position) {
        if (isHeader(position)) {
            holder.scoffHeaderTitleTv.setText(dataScoff.get(position));
            holder.scoffHeaderAuthorTv.setText(dataScoff.get(position));
          //  Log.d("HomeRecommondGameAdapte", dataScoff.get(position));
            return;
        }
        holder.scoffDetailTitleTv.setText(dataScoff.get(position - 1));
        holder.scoffDetailAuthorTv.setText(dataScoff.get(position - 1));
    }

    @Override
    public int getItemCount() {
        return dataScoff != null ? dataScoff.size() : 0;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    class HomeGameTalkScoffViewHolder extends RecyclerView.ViewHolder{
        TextView scoffHeaderTitleTv;
        TextView scoffHeaderAuthorTv;
        TextView scoffDetailTitleTv;
        TextView scoffDetailAuthorTv;
        public HomeGameTalkScoffViewHolder(View itemView) {
            super(itemView);
            scoffHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            scoffHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            scoffDetailTitleTv = (TextView)itemView.findViewById(R.id.gametalk_scoff_detail_title_tv);
            scoffDetailAuthorTv = (TextView)itemView.findViewById(R.id.gametalk_scoff_detail_author_tv);
        }
    }


}
