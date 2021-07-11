package com.widget.aaaview.custom_view.gif;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.widget.R;
import com.widget.banner.banner2.ImageUtil;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

public class GifDemoActivity extends AppCompatActivity {
    private Button btStart;
    private Button btPause;
    private Button btClose;
    private ImageView gifView;
    private String bannerGifUrl = "https://cimg1.fenqile.com/product200/M00/ex/20210702195209-4677c0ef-5d41-4fdf-a6d2-87643ae3013f.gif";
    private String bannerCycleGifUrl = "https://cimg1.fenqile.com/product200/M00/ex/20210702195222-f5c3fc26-135b-42e7-9969-123d67ce4e1d.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_demo);

        btStart = findViewById(R.id.bt_start_gif);
        btPause = findViewById(R.id.bt_pause_gif);
        btClose = findViewById(R.id.bt_close_gif);
        gifView = findViewById(R.id.gif_view);
        createGifView();

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGifView();
            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GifDrawable drawable = ((GifDrawable) gifView.getDrawable());
                if(drawable.isRunning()) {
                    drawable.stop();
                } else {
                    drawable.start();
                }
            }
        });

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消加载
                //Glide.with(GifDemoActivity.this).clear(gifView);
                gifView.setVisibility(View.GONE);
            }
        });
    }


    private void createGifView() {
        Log.i("AppScroll", "bright8#Banner#createGifView#gifView:" + gifView);
        //final WeakReference<ImageView> weakReference = new WeakReference<>(gifView);
        //weakReference.get().setVisibility(View.VISIBLE);


        //gifView.setVisibility(View.VISIBLE);
        /*LinearLayout.LayoutParams layoutParams = ((LinearLayout.LayoutParams) gifView.getLayoutParams());
        LinearLayout.LayoutParams layoutParams2 = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        layoutParams2.height = 1;
       gifView.setLayoutParams(layoutParams2);*/
        ImageUtil.loadOneTimeGif(bannerGifUrl, gifView, new ImageUtil.GifListener() {
            @Override
            public void gifPlayComplete() {
                Log.i("AppScroll", "bright8#Banner#gifPlayComplete");
                ImageView tempGifView = gifView;
                if(tempGifView != null) {
                    ImageUtil.loadImage(bannerCycleGifUrl, tempGifView, GifDrawable.LOOP_FOREVER, false, false);
                }

            }

            @Override
            public void gifPlayError() {
                Log.i("AppScroll", "bright8#Banner#gifPlayError");
            }

        });

       //ImageUtil.loadCompat(gifView, bannerGifUrl, new FitCenter());

    }

}
