package com.lanou.yindongge.music.pineapple.home.laugh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class HomeLaughAdapter extends RecyclerView.Adapter {
    private Context context;

    public HomeLaughAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View viewPeople = LayoutInflater.from(context).inflate(R.layout.item_home_laugh_people, null);
                holder = new HomeLaughPeopleViewHolder(viewPeople);
                break;
            case 1:
                View viewScoff = LayoutInflater.from(context).inflate(R.layout.item_home_laugh_scoff, null);
                holder = new HomeLaughScoffViewHolder(viewScoff);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                // 抽取跨列的布局
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeLaughPeopleViewHolder holderPeople = (HomeLaughPeopleViewHolder)holder;
                holderPeople.peopleTv.setText("真人段子秀");
                // 构建嵌套的适配器
                final HomeLaughPeopleAdapter peopleAdapter = new HomeLaughPeopleAdapter(context, view);
                List<String> dataPeople =  new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dataPeople.add("题目");
                }
                peopleAdapter.setDataPeople(dataPeople);
                // 网格布局设置两列
                GridLayoutManager peopleManager = new GridLayoutManager(context, 2);
                // 布局管理者设置跨列
                peopleManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return peopleAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                // 设置布局类型,连接适配器
                holderPeople.peopleRv.setLayoutManager(peopleManager);
                holderPeople.peopleRv.setAdapter(peopleAdapter);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeLaughScoffViewHolder holderScoff = (HomeLaughScoffViewHolder)holder;
                holderScoff.scoffTv.setText("搞笑吐槽");
                final HomeLaughScoffAdapter scoffAdapter = new HomeLaughScoffAdapter(context, view);
                List<String> dataScoff =  new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dataScoff.add("题目");
                }
                scoffAdapter.setDataScoff(dataScoff);
                GridLayoutManager scoffManager = new GridLayoutManager(context, 2);
                scoffManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return scoffAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                holderScoff.scoffRv.setLayoutManager(scoffManager);
                holderScoff.scoffRv.setAdapter(scoffAdapter);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    class HomeLaughPeopleViewHolder extends RecyclerView.ViewHolder{
        TextView peopleTv;
        RecyclerView peopleRv;
        public HomeLaughPeopleViewHolder(View itemView) {
            super(itemView);
            peopleTv = (TextView) itemView.findViewById(R.id.laugh_people_tv);
            peopleRv = (RecyclerView) itemView.findViewById(R.id.laugh_people_rv);
        }
    }
    class HomeLaughScoffViewHolder extends RecyclerView.ViewHolder{
        TextView scoffTv;
        RecyclerView scoffRv;
        public HomeLaughScoffViewHolder(View itemView) {
            super(itemView);
            scoffTv = (TextView) itemView.findViewById(R.id.laugh_scoff_tv);
            scoffRv = (RecyclerView) itemView.findViewById(R.id.laugh_scoff_rv);
        }
    }
}
