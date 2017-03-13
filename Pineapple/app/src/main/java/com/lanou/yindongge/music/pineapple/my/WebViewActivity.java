package com.lanou.yindongge.music.pineapple.my;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
        WebView webView = byView(R.id.webView);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        webView.loadUrl(result);



//        // 设置相关的webview参数
//        WebSettings set = webView.getSettings();
//        // 设置能否加载js脚本
//        set.setJavaScriptEnabled(true);
    }
}
