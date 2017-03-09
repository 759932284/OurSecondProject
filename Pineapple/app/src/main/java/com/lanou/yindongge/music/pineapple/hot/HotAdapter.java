package com.lanou.yindongge.music.pineapple.hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by dllo on 17/2/23.
 */

public class HotAdapter extends BaseAdapter {
    private Context context;
    private int[] colors;

    public HotAdapter(Context context, int[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return colors == null ? 0 : colors.length;
    }

    @Override
    public Object getItem(int i) {
        return colors == null ? null : colors[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600, 600);
        LinearLayout ll = new LinearLayout(context);
        ll.setBackgroundColor(colors[i]);
        ll.setLayoutParams(params);
        return ll;
    }
}
