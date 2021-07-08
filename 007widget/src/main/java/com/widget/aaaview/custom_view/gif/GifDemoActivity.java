package com.widget.aaaview.custom_view.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.widget.R;
import com.widget.banner.banner2.ImageUtil;

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
                //gifView.setVisibility(View.GONE);
            }
        });

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(GifDemoActivity.this).clear(gifView);
                Drawable drawable = null;
                Glide.with(GifDemoActivity.this).load(drawable).into(gifView);
                gifView.setVisibility(View.GONE);
            }
        });
    }


    private void createGifView() {
        gifView.setVisibility(View.VISIBLE);
        ImageUtil.loadOneTimeGif(bannerGifUrl, gifView, new ImageUtil.GifListener() {
            @Override
            public void gifPlayComplete() {
                Log.i("AppScroll", "bright8#Banner#gifPlayComplete");
                ImageUtil.loadImage(bannerCycleGifUrl, gifView, GifDrawable.LOOP_FOREVER, false, false);
            }

            @Override
            public void gifPlayError() {
                Log.i("AppScroll", "bright8#Banner#gifPlayError");
            }

        });

    }

}
