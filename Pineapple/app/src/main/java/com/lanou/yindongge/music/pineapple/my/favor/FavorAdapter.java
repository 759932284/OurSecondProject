package com.lanou.yindongge.music.pineapple.my.favor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.lanou.yindongge.music.pineapple.net.GlideManager;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/3/10.
 */

public class FavorAdapter extends BaseAdapter {
    private Context context;
    private List<FavorEntity> list;

    public FavorAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<FavorEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list != null ? list.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FavorHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_favor, viewGroup, false);
            holder = new FavorHolder(view);
            view.setTag(holder);
        } else {
            holder = (FavorHolder) view.getTag();
        }

        ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 5 * 2;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 6;
        holder.imageView.setLayoutParams(lp);


        GlideManager.getGlideManager().loadImageView(context, list.get(i).getImgStr(), holder.imageView);
        holder.titleTv.setText(list.get(i).getTitle().toString());
        holder.channalTv.setText(list.get(i).getAuthor());
        return view;
    }

    class FavorHolder {
        ImageView imageView;
        TextView titleTv, channalTv;

        public FavorHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.favor_iv);
            titleTv = (TextView) view.findViewById(R.id.favor_tv);
            channalTv = (TextView) view.findViewById(R.id.channal_tv);
        }
    }
}

