package com.lanou.yindongge.music.pineapple.home.gametalk;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.detail.PlayDetailActivity;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeGameTalkFragment extends BaseFragment {

    private RecyclerView gameTalkRv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_gametalk;
    }

    @Override
    public void initView(View view) {
        gameTalkRv = byView(R.id.game_talk_rv);
    }

    @Override
    public void initData() {
        HomeGameTalkAdapter gameTalkAdapter = new HomeGameTalkAdapter(context);
        gameTalkRv.setAdapter(gameTalkAdapter);
        gameTalkRv.setLayoutManager(new LinearLayoutManager(context));

//        gameTalkAdapter.setOnVideoPlayListener(this);
    }

//    @Override
//    public void onClick(int position) {
//        startActivity(new Intent(context, PlayDetailActivity.class));
//    }
}
