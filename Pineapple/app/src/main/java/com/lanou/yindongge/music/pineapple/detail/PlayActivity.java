package com.lanou.yindongge.music.pineapple.detail;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseActivity;
import com.lanou.yindongge.music.pineapple.bean.FavorEntity;
import com.lanou.yindongge.music.pineapple.bean.GameTalkMoreResponse;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.lang.reflect.Type;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Vitamio视频播放框架Demo
 */

public class PlayActivity extends BaseActivity implements MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, View.OnClickListener, OnNetResultListener {

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
    private ImageView shareIv;
    private TextView detailTitleTv;

//    // 记录切换横竖屏播放的记录1
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        this.savedInstanceState = savedInstanceState;
//        super.onCreate(savedInstanceState);
//        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_play;
    }

    private void initView() {
        detailTitleTv = byView(R.id.detail_title_tv);

        shareIv = (ImageView)findViewById(R.id.detail_share_iv);
        shareIv.setOnClickListener(this);
        saveIv = byView(R.id.detail_store_iv);
        saveIv.setOnClickListener(this);

        mVideoView = (VideoView) findViewById(R.id.buffer);

        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);

        // 两种recyclerView
        stageRv = (RecyclerView) findViewById(R.id.detail_stage_rv);
        moreRecommondRv = (RecyclerView) findViewById(R.id.detail_more_recommond_rv);
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
        initView();
        // 创建数据库
        liteOrm = LiteOrm.newSingleInstance(this, "video.db");
        mCustomMediaController = new CustomMediaController(this, mVideoView, this);

        // 接收从视频界面传过来的数据
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

        if (savedInstanceState != null) {
            pos = savedInstanceState.getLong(KEY_POS, 0);
            Log.d("PlayActivity", "456:" + pos);
        }

        initDatas();
        // 网络请求多少集的数据
        OkHttpManager.getInstance().startGetRequest(Contant.GAME_TALK_MORE, Contant.GAME_TALK_MORE_REQUESTCODE, this);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//
//        outState.putLong(KEY_POS, mVideoView.getCurrentPosition());
//        super.onSaveInstanceState(outState);
//    }

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
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    // 收藏按钮
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_store_iv:
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
                } else if (listQuery.size() > 0) {
                    liteOrm.delete(listQuery);
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    saveIv.setImageResource(R.mipmap.video_player_favor);
                }
                break;
            case R.id.detail_share_iv:
                showShare();
                break;
        }

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
        oks.show(this);
    }

    private FavorEntity buildData() {
        FavorEntity favorEntity = new FavorEntity();
        favorEntity.setTitle(title);
        favorEntity.setAuthor(author);
        favorEntity.setImgStr(imgStr);
        favorEntity.setUrl(path);
        return favorEntity;
    }

    @Override
    public void onSuccessListener(String result, int requestCode) {
        if (requestCode == Contant.GAME_TALK_MORE_REQUESTCODE) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<GameTalkMoreResponse>>() {
            }.getType();
            List<GameTalkMoreResponse> dataMore = gson.fromJson(result, type);
            // 创建多少集的适配器,并且传入解析后的数据
            PlayStageAdapter playStageAdapter = new PlayStageAdapter(this);
            playStageAdapter.setDataMore(dataMore);
            stageRv.setAdapter(playStageAdapter);
            stageRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            // 创建更多推荐的适配器, 并且传入解析后的数据
            PlayRecommondAdapter playRecommondAdapter = new PlayRecommondAdapter(this);
            playRecommondAdapter.setDataMore(dataMore);
            moreRecommondRv.setAdapter(playRecommondAdapter);
            moreRecommondRv.setLayoutManager(new LinearLayoutManager(this));
            detailTitleTv.setText(title);
        }

    }

    @Override
    public void onFailureListener(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}
