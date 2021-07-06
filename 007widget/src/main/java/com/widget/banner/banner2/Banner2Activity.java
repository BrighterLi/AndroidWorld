package com.widget.banner.banner2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

public class Banner2Activity extends AppCompatActivity {
    private Banner mBanner;
    private List<String> mBannerDataList = new ArrayList<>();
    private BannerAdapter<String> mBannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);

    }

    private void initBanner() {
        mBanner.setBannerScrolledListener(new Banner.BannerScrolledListener() {
            @Override
            public void onPageScrolledOffset(float positionOffset, int position, int size) {

            }

            @Override
            public void onPageSelected(int position, boolean isAutoScroll) {

            }
        });

        mBannerAdapter = new BannerAdapter<String>(mBannerDataList) {
            @Override
            public View createView(ViewGroup container, String s, int position) {
                ImageView iv = new ImageView(getApplicationContext());
                iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT/*mImgParams.width*/, /*mImgParams.height*/ViewGroup.LayoutParams.MATCH_PARENT));
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //iv.setImageResource(R.drawable.ic_index_img_holder);
                //GrayManager.getInstance().grayImageView(iv);
                return iv;
            }

            @Override
            public void bindData(View view, String s, int position) {
                if (view instanceof ImageView) {
                    ImageUtil.loadImage(s, (ImageView) view, true);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                }
            }
        };
    }

    private void setBannerData() {
        /*mBanner = new Banner(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)ScreenUtil.dip2px(mContext, 161));
        lp.addRule(RelativeLayout.ABOVE, mLlScrollContainer.getId());
        lp.leftMargin = 8;
        lp.rightMargin = 5;
        mBanner.setLayoutParams(lp);
        //mAppScroll.addView(mBanner);
        addView(mBanner, getChildCount() -2);*/
        if(mBannerDataList != null && mBannerDataList.size() >0) {
            initBanner();
            mBanner.setRepeatSwitchTimes(1);
            mBanner.setBannerSwitchTime(3500);
            mBannerAdapter.setData(mBannerDataList);
            mBanner.setAdapter(mBannerAdapter, false,false);
            //mBanner.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mBannerAdapter.setLoop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBanner = findViewById(R.id.mBanner);
        mBannerDataList.add("https://cimg1.fenqile.com/product200/M00/ex/20210702195209-4677c0ef-5d41-4fdf-a6d2-87643ae3013f.gif");
        mBannerDataList.add("https://cimg1.fenqile.com/product200/M00/ex/20210702195222-f5c3fc26-135b-42e7-9969-123d67ce4e1d.gif");
        setBannerData();
    }
}
