package com.lanou.yindongge.music.pineapple.home.laugh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/22.
 */

public class HomeLaughFragment extends BaseFragment {

    private RecyclerView laughRv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_laugh;
    }

    @Override
    public void initView(View view) {
        laughRv = (RecyclerView)view.findViewById(R.id.laugh_rv);
    }

    @Override
    public void initData() {
        HomeLaughAdapter laughAdapter = new HomeLaughAdapter(context);
        laughRv.setAdapter(laughAdapter);
        laughRv.setLayoutManager(new LinearLayoutManager(context));
    }
}
