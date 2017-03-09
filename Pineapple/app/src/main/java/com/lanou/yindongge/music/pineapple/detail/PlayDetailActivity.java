package com.lanou.yindongge.music.pineapple.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;

public class PlayDetailActivity extends BaseActivity implements View.OnClickListener{

    private VideoView videoView;
    private Button play;
    private Button pause;
    private Button replay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_detail;
    }

    @Override
    public void initData() {
        initView();
        setListener();
        initVideoPath();
    }

    private void initVideoPath() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        videoView.setVideoURI(Uri.parse(url));
    }

    private void setListener() {
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.video_view);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        replay = (Button) findViewById(R.id.replay);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
