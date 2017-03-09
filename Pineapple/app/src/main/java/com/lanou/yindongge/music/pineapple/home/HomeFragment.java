package com.lanou.yindongge.music.pineapple.home;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.home.gametalk.HomeGameTalkFragment;
import com.lanou.yindongge.music.pineapple.home.recommond.HomeRecommondFragment;
import com.lanou.yindongge.music.pineapple.util.Contant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewPagerAdapter mainAdapter;
    private String[] title = {"推荐", "游戏杂谈", "搞笑",
            "动画", "萌宠", "美食", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        tabLayout = byView(R.id.tabLayout);
        viewPager = byView(R.id.viewPager);

    }
//    "搞笑", "动画", "萌宠", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说", "守望先锋"
    @Override
    public void initData() {
        mainAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mainAdapter);
        mainAdapter.setTitle(title);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeRecommondFragment());

//        fragments.add(new HomeGameTalkFragment());
//        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_GAME_TALK, "游戏杂谈"));
        // 复用的所有碎片
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_GAME_TALK, "游戏杂谈"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_LAUGH, "搞笑"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_CATOON, "动画"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_ANIMAL, "萌宠"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_FOOD, "美食"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_QUADRATIC, "二次元"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_AMUSE, "娱乐"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_NET, "网剧"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_HERO, "英雄联盟"));
        fragments.add(HomeCommentFragment.newInstance(Contant.HOME_LEGEND, "炉石传说"));

        mainAdapter.setFragments(fragments);
    }
}
