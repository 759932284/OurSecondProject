package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{
    private Context context;

    private List<SearchBean> searchList;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchList(List<SearchBean> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_search_search, parent, false);
        SearchHolder holder = new SearchHolder(itemView);
//        ViewGroup.LayoutParams lp = holder.searchBtn.getLayoutParams();
//        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH);
//        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 16;
//        holder.searchBtn.setLayoutParams(lp);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.searchBtn.setText(searchList.get(position).getKeyword());
    }

    @Override
    public int getItemCount() {
        return searchList == null ? 0 : searchList.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        Button searchBtn;
        public SearchHolder(View itemView) {
            super(itemView);
            searchBtn = (Button) itemView.findViewById(R.id.search_Btn);
        }
    }
}
