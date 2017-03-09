package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.AlbumResponse;
import com.lanou.yindongge.music.pineapple.bean.BacteriaResponse;
import com.lanou.yindongge.music.pineapple.bean.BannerResponse;
import com.lanou.yindongge.music.pineapple.bean.GameTalkResponse;
import com.lanou.yindongge.music.pineapple.bean.PopularResponse;
import com.lanou.yindongge.music.pineapple.bean.RecommondResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeRecommondAdapter extends RecyclerView.Adapter implements OnNetResultListener {
    private Context context;
    private HomeRecommondBacteriaAdapter bacteriaAdapter;
    private HomeRecommondPopularAdapter popularAdapter;
    private HomeRecommondGameAdapter gameAdapter;
    private HomeRecommondAlbumAdapter albumAdapter;
    private HomeRecommondAlbumViewHolder holderAlbum;
    private HomeRecommondRecommondAdapter recommondAdapter;
    private RotateAdapter rotateAdapter;

    public HomeRecommondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View viewHead = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_head, null);
                holder = new HomeRecommondHeadViewHolder(viewHead);
                break;
            case 1:
                // Bacteria 菌
                View viewBacteria = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_bacteria, null);
                holder = new HomeRecommondBacteriaViewHolder(viewBacteria);
                break;
            case 2:
                // 人气周榜
                View viewPopular = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_popular, null);
                holder = new HomeRecommondPopularViewHolder(viewPopular);
                break;
            case 3:
                // 游戏杂谈
                View viewGame = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_game, null);
                holder = new HomeRecommondGameViewHolder(viewGame);
                break;
            case 4:
                // 菠萝专辑
                View viewAlbum = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_album, null);
                holder = new HomeRecommondAlbumViewHolder(viewAlbum);
                break;
            case 5:
                // 为您推荐
                View viewRecommond = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_recommond, null);
                holder = new HomeRecommondRecommondHolder(viewRecommond);
                break;


        }
        return holder;
    }

    private List<BannerResponse> dataBanner;

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:

                final HomeRecommondHeadViewHolder holderHead = (HomeRecommondHeadViewHolder) holder;
                final RotateAdapter rotateAdapter = new RotateAdapter(context);
                holderHead.mViewPager.setAdapter(rotateAdapter);

                //******************轮播图适配*************************
//                // 轮播图外面布局
                ViewGroup.LayoutParams lpOut = holderHead.bannerLl.getLayoutParams();
                lpOut.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH);
                lpOut.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 3;
                holderHead.bannerLl.setLayoutParams(lpOut);

                ViewGroup.LayoutParams lp = holderHead.mViewPager.getLayoutParams();
                lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) * 9 / 10;
            //    lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 4.0f / 5.0f);
                holderHead.mViewPager.setLayoutParams(lp);

                //******************轮播图适配*************************

                OkHttpManager.getInstance().startGetRequest(Contant.BANNER, Contant.BANNER_REQUESTCODE, new OnNetResultListener() {
                    @Override
                    public void onSuccessListener(String result, int requestCode) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<BannerResponse>>() {
                        }.getType();
                        dataBanner = gson.fromJson(result, type);
                        if (dataBanner != null) {
                            rotateAdapter.setDataBanner(dataBanner);
                        }

                        holderHead.mViewPager.setCurrentItem(10000);

                        holderHead.mViewPager.setPageMargin(-100);
                        holderHead.mViewPager.setOffscreenPageLimit(3);
                        holderHead.mViewPager.setPageTransformer(true, new ScaleInTransformer());

                    }

                    @Override
                    public void onFailureListener(String errMsg) {

                    }
                });
                break;
            case 1:
                HomeRecommondBacteriaViewHolder holderBacteria = (HomeRecommondBacteriaViewHolder) holder;
                holderBacteria.bacteriaTv.setText("菠萝菌力荐");
                bacteriaAdapter = new HomeRecommondBacteriaAdapter(context);
                // 网络请求 菠萝菌力荐
                OkHttpManager.getInstance().startGetRequest(Contant.BACTERIA, Contant.BACTERIA_REQUESTCODE, this);
                holderBacteria.bacteriaRv.setLayoutManager(new GridLayoutManager(context, 2,
                        LinearLayoutManager.VERTICAL, false));
                holderBacteria.bacteriaRv.setAdapter(bacteriaAdapter);
                break;
            case 2:
                HomeRecommondPopularViewHolder holderPopular = (HomeRecommondPopularViewHolder) holder;
                holderPopular.popularTv.setText("人气周榜");
                popularAdapter = new HomeRecommondPopularAdapter(context);
                // 获取人气榜网络数据
                OkHttpManager.getInstance().startGetRequest(Contant.POPULAR, Contant.POPULAR_REQUESTCODE, this);
                holderPopular.popularRv.setLayoutManager(new GridLayoutManager(context, 3,
                        LinearLayoutManager.HORIZONTAL, false));
                holderPopular.popularRv.setAdapter(popularAdapter);
                break;
            case 3:
                View gameHeaderView = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeRecommondGameViewHolder holderGame = (HomeRecommondGameViewHolder) holder;
                holderGame.gameTv.setText("游戏杂谈");
                gameAdapter = new HomeRecommondGameAdapter(context, gameHeaderView);
                // 获取人气榜网络数据
                OkHttpManager.getInstance().startGetRequest(Contant.GAME_TALK, Contant.GAME_TALK_REQUESTCODE, this);
                GridLayoutManager gameManager = new GridLayoutManager(context, 2);
                gameManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return gameAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                holderGame.gameRv.setLayoutManager(gameManager);
                holderGame.gameRv.setAdapter(gameAdapter);
                break;
            case 4:
                holderAlbum = (HomeRecommondAlbumViewHolder) holder;
                holderAlbum.albumTv.setText("菠萝专辑");
                // 获取网络图片后,用解析工具铺建
                //  holderAlbum.albumIv.setImageResource(R.mipmap.ic_launcher);
                albumAdapter = new HomeRecommondAlbumAdapter(context);
                OkHttpManager.getInstance().startGetRequest(Contant.ALBUM, Contant.ALBUM_REQUESTCODE, this);
                holderAlbum.albumRv.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false));
                holderAlbum.albumRv.setAdapter(albumAdapter);
                break;
            case 5:
                HomeRecommondRecommondHolder holderRecommond = (HomeRecommondRecommondHolder) holder;
                holderRecommond.recommondTv.setText("为您推荐");
                recommondAdapter = new HomeRecommondRecommondAdapter(context);
                OkHttpManager.getInstance().startGetRequest(Contant.RECOMMOND, Contant.RECOMMOND_REQUESTCODE, this);
                holderRecommond.recommondRv.setLayoutManager(new GridLayoutManager(context, 2,
                        LinearLayoutManager.VERTICAL, false));
                holderRecommond.recommondRv.setAdapter(recommondAdapter);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    // 网络获取成功后的操作
    @Override
    public void onSuccessListener(String result, int requestCode) {
        Gson gson = new Gson();
        // 推荐-菠萝菌力荐-解析
        if (Contant.BACTERIA_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<BacteriaResponse>>() {
            }.getType();
            List<BacteriaResponse> dataBacteria = gson.fromJson(result, type);
            bacteriaAdapter.setDataBacteria(dataBacteria);
        }
        // 推荐-人气榜-解析
        if (Contant.POPULAR_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<PopularResponse>>() {
            }.getType();
            List<PopularResponse> dataPopular = gson.fromJson(result, type);
            popularAdapter.setDataPopular(dataPopular);
        }
        // 推荐-游戏杂谈-解析
        if (Contant.GAME_TALK_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<GameTalkResponse>>() {
            }.getType();
            List<GameTalkResponse> dataGameTalkAll = gson.fromJson(result, type);
            List<GameTalkResponse.VideoListBean> dataGameTalk = dataGameTalkAll.get(0).getVideoList();
            gameAdapter.setDataGameTalk(dataGameTalk);
        }
        // 推荐-菠萝专辑-解析
        if (Contant.ALBUM_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<AlbumResponse>>() {
            }.getType();
            List<AlbumResponse> dataAlbumAll = gson.fromJson(result, type);
            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                    dataAlbumAll.get(0).getCover(), holderAlbum.albumIv);

            // 适配屏幕宽高
            ViewGroup.LayoutParams lpHeader = holderAlbum.albumIv.getLayoutParams();
            lpHeader.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH);
            lpHeader.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 3;
            holderAlbum.albumIv.setLayoutParams(lpHeader);

            holderAlbum.albumInnerTv.setText(dataAlbumAll.get(0).getName());
            List<AlbumResponse.VideoListBean> dataAlbum = dataAlbumAll.get(0).getVideoList();
            albumAdapter.setDataAlbum(dataAlbum);
        }
        // 推荐-为您推荐-解析
        if (Contant.RECOMMOND_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<RecommondResponse>>() {
            }.getType();
            List<RecommondResponse> dataRecommond = gson.fromJson(result, type);
            recommondAdapter.setDataRecommond(dataRecommond);
        }
    }

    @Override
    public void onFailureListener(String errMsg) {
        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }

    class HomeRecommondBacteriaViewHolder extends RecyclerView.ViewHolder {
        RecyclerView bacteriaRv;
        TextView bacteriaTv;

        public HomeRecommondBacteriaViewHolder(View itemView) {
            super(itemView);
            bacteriaRv = (RecyclerView) itemView.findViewById(R.id.bacteria_rv);
            bacteriaTv = (TextView) itemView.findViewById(R.id.bacteria_tv);
        }
    }

    class HomeRecommondHeadViewHolder extends RecyclerView.ViewHolder {
        ViewPager mViewPager;
        LinearLayout bannerLl;
    //    LinearLayout bannerRoot;

        public HomeRecommondHeadViewHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.id_viewpager);
            bannerLl = (LinearLayout) itemView.findViewById(R.id.banner_ll);
         //   bannerRoot = (LinearLayout) itemView.findViewById(R.id.banner_root);
        }
    }

    class HomeRecommondPopularViewHolder extends RecyclerView.ViewHolder {
        RecyclerView popularRv;
        TextView popularTv;

        public HomeRecommondPopularViewHolder(View itemView) {
            super(itemView);
            popularRv = (RecyclerView) itemView.findViewById(R.id.recommond_popular_rv);
            popularTv = (TextView) itemView.findViewById(R.id.recommond_popular_tv);
        }
    }

    class HomeRecommondGameViewHolder extends RecyclerView.ViewHolder {
        RecyclerView gameRv;
        TextView gameTv;

        public HomeRecommondGameViewHolder(View itemView) {
            super(itemView);
            gameRv = (RecyclerView) itemView.findViewById(R.id.recommond_game_rv);
            gameTv = (TextView) itemView.findViewById(R.id.recommond_game_tv);
        }
    }

    class HomeRecommondAlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumTv;
        ImageView albumIv;
        RecyclerView albumRv;
        TextView albumInnerTv;

        public HomeRecommondAlbumViewHolder(View itemView) {
            super(itemView);
            albumTv = (TextView) itemView.findViewById(R.id.recommond_album_tv);
            albumIv = (ImageView) itemView.findViewById(R.id.recommond_album_iv);
            albumRv = (RecyclerView) itemView.findViewById(R.id.recommond_album_rv);
            albumInnerTv = (TextView) itemView.findViewById(R.id.recommond_album_inner_tv);
        }
    }

    class HomeRecommondRecommondHolder extends RecyclerView.ViewHolder {
        TextView recommondTv;
        RecyclerView recommondRv;

        public HomeRecommondRecommondHolder(View itemView) {
            super(itemView);
            recommondTv = (TextView) itemView.findViewById(R.id.recommond_tv);
            recommondRv = (RecyclerView) itemView.findViewById(R.id.recommond_rv);
        }
    }
}
