package com.lanou.yindongge.music.pineapple.find;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;


/**
 *  搜索点击后跳转
 */
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

        TextView cancel = byView(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
