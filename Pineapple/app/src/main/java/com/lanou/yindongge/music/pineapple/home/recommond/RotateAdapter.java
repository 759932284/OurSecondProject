package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.BannerResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.util.ScreenSizeUtils;
import com.lanou.yindongge.music.pineapple.util.ScreenState;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

/**
 * 首页-推荐-轮播图适配器
 */
public class RotateAdapter extends PagerAdapter {
    private Context context;
    private List<BannerResponse> dataBanner;

    public void setDataBanner(List<BannerResponse> dataBanner) {
        this.dataBanner = dataBanner;
        notifyDataSetChanged();
    }

    public RotateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

            int newPosition = position % dataBanner.size();

            View view = LayoutInflater.from(context).inflate(R.layout.item_rotate, null);
//
            ImageView imageView = (ImageView) view.findViewById(R.id.rotate_iv);
            TextView textView = (TextView) view.findViewById(R.id.rotate_tv);
            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                    dataBanner.get(newPosition).getCover(), imageView);

        // 适配屏幕宽高
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = ScreenSizeUtils.getSreen(context, ScreenState.WIDTH) / 7 * 5;
        lp.height = ScreenSizeUtils.getSreen(context, ScreenState.HEIGHT) / 7 * 2;
        imageView.setLayoutParams(lp);

            textView.setText(dataBanner.get(newPosition).getTitle());

            container.addView(view);
        return view;

//            ViewParent viewParent = view.getParent();
//            if (viewParent!=null){
//                ViewGroup parent = (ViewGroup)viewParent;
//                parent.removeView(view);
//            }
//            view = new ImageView(context);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
//                    dataBanner.get(position).getCover(), view);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
//            ViewParent viewParent = view.getParent();
//            if (viewParent!=null){
//                ViewGroup parent = (ViewGroup)viewParent;
//                parent.removeView(view);
//            }
//            container.addView(view);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     //   container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return dataBanner == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
