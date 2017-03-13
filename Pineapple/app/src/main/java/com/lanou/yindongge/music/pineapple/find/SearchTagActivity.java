package com.lanou.yindongge.music.pineapple.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;

public class SearchTagActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        String searchTag = intent.getStringExtra("searchTag");

        EditText searchEt = (EditText) findViewById(R.id.search_et);
        searchEt.setText(searchTag);
    }
}
