package com.lanou.yindongge.music.pineapple.my.favor;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.lanou.yindongge.music.pineapple.detail.PlayActivity;
import com.litesuits.orm.LiteOrm;

import java.util.List;

public class FavorActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lv;
    private List<FavorEntity> favorEntities;

    @Override
    public int getLayoutId() {
        return R.layout.activity_favor;
    }

    @Override
    public void initData() {
        // 单例数据库
        LiteOrm liteOrm = LiteOrm.newSingleInstance(this, "video.db");
        FavorAdapter favorAdapter = new FavorAdapter(this);
        lv = (ListView) findViewById(R.id.listView);
        favorEntities = liteOrm.query(FavorEntity.class);
        favorAdapter.setList(favorEntities);
        lv.setAdapter(favorAdapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = favorEntities.get(i).getUrl();
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}

