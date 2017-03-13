package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


    private OnSearchTagClickListener onSearchTagClickListener;

    public void setOnSearchTagClickListener(OnSearchTagClickListener onSearchTagClickListener) {
        this.onSearchTagClickListener = onSearchTagClickListener;
        notifyDataSetChanged();
    }

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
        return holder;
    }

    @Override
    public void onBindViewHolder(final SearchHolder holder, int position) {
        holder.searchTv.setText(searchList.get(position).getKeyword());
        // 标签点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                onSearchTagClickListener.onSearchTagClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList == null ? 0 : searchList.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        TextView searchTv;
        public SearchHolder(View itemView) {
            super(itemView);
            searchTv = (TextView) itemView.findViewById(R.id.search_tv);
        }
    }

    interface OnSearchTagClickListener {
        void onSearchTagClick(int position);
    }
}
