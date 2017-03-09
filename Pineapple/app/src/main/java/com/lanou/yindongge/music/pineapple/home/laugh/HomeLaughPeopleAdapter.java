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

public class HomeLaughPeopleAdapter extends RecyclerView.Adapter<HomeLaughPeopleAdapter.HomeLaughPeopleViewHolder> {
    private Context context;
    private View viewHeader;
    private List<String> dataPeople;

    public void setDataPeople(List<String> dataPeople) {
        this.dataPeople = dataPeople;
        notifyDataSetChanged();
    }

    public HomeLaughPeopleAdapter(Context context, View viewHeader) {
        this.context = context;
        this.viewHeader = viewHeader;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public HomeLaughPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeLaughPeopleViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeLaughPeopleViewHolder(viewHeader);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_laugh_people_detail, null);
                holder = new HomeLaughPeopleViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeLaughPeopleViewHolder holder, int position) {
        if (isHeader(position)) {
            holder.peopleHeaderTitleTv.setText(dataPeople.get(position));
            holder.peopleHeaderAuthorTv.setText(dataPeople.get(position));
            return;
        }
        holder.peopleDetailTitleTv.setText(dataPeople.get(position - 1));
        holder.peopleDetailAuthorTv.setText(dataPeople.get(position - 1));
    }

    @Override
    public int getItemCount() {
        return dataPeople != null ? dataPeople.size() : 0;
    }

    public boolean isHeader(int position) {
//        if (position == 0) {
//            return true;
//        } else return false;
        return position == 0;
    }

    class HomeLaughPeopleViewHolder extends RecyclerView.ViewHolder{
        TextView peopleHeaderTitleTv;
        TextView peopleHeaderAuthorTv;
        TextView peopleDetailTitleTv;
        TextView peopleDetailAuthorTv;
        public HomeLaughPeopleViewHolder(View itemView) {
            super(itemView);
            peopleHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            peopleHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            peopleDetailTitleTv = (TextView)itemView.findViewById(R.id.laugh_people_detail_title_tv);
            peopleDetailAuthorTv = (TextView)itemView.findViewById(R.id.laugh_people_detail_author_tv);
        }
    }
}
