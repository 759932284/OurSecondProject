package com.lanou.yindongge.music.pineapple;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.lanou.yindongge.music.pineapple.base.BaseActivity;
import com.lanou.yindongge.music.pineapple.find.FindFragment;
import com.lanou.yindongge.music.pineapple.home.HomeFragment;
import com.lanou.yindongge.music.pineapple.hot.HotFragment;
import com.lanou.yindongge.music.pineapple.my.MyFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        initClick();
        initFirstView();

    }

    private void initFirstView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.take_place_fl, new HomeFragment());
        transaction.commit();
    }

    private void initClick() {
        Button homeBtn = byView(R.id.home_btn);
        Button hotBtn = byView(R.id.hot_btn);
        Button findBtn = byView(R.id.find_btn);
        Button myBtn = byView(R.id.my_btn);
        homeBtn.setOnClickListener(this);
        hotBtn.setOnClickListener(this);
        findBtn.setOnClickListener(this);
        myBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.home_btn:
                transaction.replace(R.id.take_place_fl, new HomeFragment());
                break;
            case R.id.hot_btn:
                transaction.replace(R.id.take_place_fl, new HotFragment());
                break;
            case R.id.find_btn:
                transaction.replace(R.id.take_place_fl, new FindFragment());
                break;
            case R.id.my_btn:
                transaction.replace(R.id.take_place_fl, new MyFragment());
                break;
        }
        transaction.commit();
    }

}
