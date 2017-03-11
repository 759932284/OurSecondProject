package com.lanou.yindongge.music.pineapple.my.favor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.litesuits.orm.LiteOrm;

import java.util.List;

public class FavorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor);

        RecyclerView favorRv = (RecyclerView)findViewById(R.id.favor_rv);
        LiteOrm liteOrm = LiteOrm.newSingleInstance(this, "video.db");
        FavorAdapter favorAdapter = new FavorAdapter(this);
        List<FavorEntity> dataFavor = liteOrm.query(FavorEntity.class);
        favorAdapter.setDataFavor(dataFavor);
        favorRv.setAdapter(favorAdapter);
        favorRv.setLayoutManager(new LinearLayoutManager(this));
    }
}
