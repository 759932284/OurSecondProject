package com.lanou.yindongge.music.pineapple.detail;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Vitamio视频播放框架Demo
 */

public class PlayActivity extends BaseActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, View.OnClickListener{

    //视频地址
    private String path;
//            = "http://baobab.wdjcdn.com/145076769089714.mp4";
    private String title;
    private Uri uri;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private CustomMediaController mCustomMediaController;
    private VideoView mVideoView;
    //*******************

    private static final String KEY_POS = "key_pos";
    private long pos = 0;
    private Bundle savedInstanceState;
    private boolean first = true;
    private RecyclerView stageRv;
    private RecyclerView moreRecommondRv;
    private LiteOrm liteOrm;
    private String author;
    private String imgStr;
    private List<FavorEntity> listQuery;
  //  private List<FavorEntity> list;
    private FavorEntity favorEntity;
    private ImageView saveIv;

    // 记录切换横竖屏播放的记录1
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_play;
    }

    private void initView() {
        saveIv = byView(R.id.detail_store_iv);
        saveIv.setOnClickListener(this);

        mVideoView = (VideoView) findViewById(R.id.buffer);

        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);

        // 两种recyclerView
        stageRv = (RecyclerView) findViewById(R.id.detail_stage_rv);
        moreRecommondRv = (RecyclerView)findViewById(R.id.detail_more_recommond_rv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        QueryBuilder<FavorEntity> qb = new QueryBuilder<>(FavorEntity.class);
        qb.where("title = ?", new Object[]{title});
        listQuery = liteOrm.query(qb);
        if (listQuery.size() > 0) {
            saveIv.setImageResource(R.mipmap.video_player_favored);
        }
    }

    @Override
    public void initData() {
        // 创建数据库
        liteOrm = LiteOrm.newSingleInstance(this, "video.db");



        mCustomMediaController=new CustomMediaController(this,mVideoView,this);

        Intent intent = getIntent();
        path = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        imgStr = intent.getStringExtra("imgStr");



        mCustomMediaController.setVideoName(title);


        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = PlayActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        //设置视频解码监听
//        if (!LibsChecker.checkVitamioLibs(this)) {
//            return;
//        }
        // 记录切换横竖屏播放的记录

        if (savedInstanceState != null){
                pos = savedInstanceState.getLong(KEY_POS,0);
            Log.d("PlayActivity", "456:" + pos);
            }

        initView();
        initDatas();

        // 创建更新至多少集的适配器,并传入数据
        PlayStageAdapter playStageAdapter = new PlayStageAdapter(this);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("具体内容");
        }
        playStageAdapter.setData(data);
        if (stageRv != null) {
            stageRv.setAdapter(playStageAdapter);
            stageRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }

        // 创建更多推荐的适配器,并传入数据
        PlayRecommondAdapter playRecommondAdapter = new PlayRecommondAdapter(this);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("详细内容");
        }
        playRecommondAdapter.setDatas(datas);
        if (moreRecommondRv != null) {
            moreRecommondRv.setAdapter(playRecommondAdapter);
            moreRecommondRv.setLayoutManager(new LinearLayoutManager(this));
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putLong(KEY_POS,mVideoView.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }

    private void initDatas() {


        uri = Uri.parse(path);
        mVideoView.setVideoURI(uri);//设置视频播放地址

        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    if (first) {
                        mp.seekTo(pos);
                        first = false;
                    }
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);
                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:

                // 记录切换横竖屏播放的记录
                if (first) {
                    mp.seekTo(pos);

                    first = false;
                }
                mVideoView.start();
                //***************

                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        //屏幕切换时，设置全屏
//        if (mVideoView != null){
//            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT, 0);
//        }
        super.onConfigurationChanged(newConfig);
    }

    // 收藏按钮
    @Override
    public void onClick(View v) {
        // 按title查询
        QueryBuilder<FavorEntity> qb = new QueryBuilder<>(FavorEntity.class);
        qb.where("title = ?", new Object[]{title});
        listQuery = liteOrm.query(qb);
        Log.d("PlayActivity", "listQuery.size():" + listQuery.size());

        if (listQuery.size() == 0) {
            favorEntity = buildData();
            liteOrm.insert(favorEntity);
            Toast.makeText(this, "加入收藏", Toast.LENGTH_SHORT).show();
            saveIv.setImageResource(R.mipmap.video_player_favored);
        }
        else if(listQuery.size() > 0){
            liteOrm.delete(listQuery);
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
            saveIv.setImageResource(R.mipmap.video_player_favor);
        }
    }

//    private List<FavorEntity> buildData() {
//        List<FavorEntity> dataFavor = new ArrayList<>();
//        FavorEntity favorEntity = new FavorEntity();
//        favorEntity.setTitle(title);
//        favorEntity.setAuthor(author);
//        favorEntity.setImgStr(imgStr);
//        favorEntity.setUrl(path);
//        dataFavor.add(favorEntity);
//        return dataFavor;
//    }
    private FavorEntity buildData(){
        FavorEntity favorEntity = new FavorEntity();
        favorEntity.setTitle(title);
        favorEntity.setAuthor(author);
        favorEntity.setImgStr(imgStr);
        favorEntity.setUrl(path);
        return favorEntity;
    }
}
