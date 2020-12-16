package com.xiaoming.net.frame.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiaoming.net.R;

public class GlideImgActivity extends Activity {
    private ImageView mIv;
    private Button mBt;
    private Button mBtBack;
    private static String  URL = "http://p0.img.360kuai.com/dmfd/160_90_/t0188ba7607868b56e3.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        mIv = findViewById(R.id.iv_glide);
        mBt = findViewById(R.id.bt_glide);
        mBtBack = findViewById(R.id.bt_back);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadImage2();
            }
        });
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void startLoadImage() {
        Glide.with(this)
                .load(URL)
                .diskCacheStrategy( DiskCacheStrategy.ALL ) //image的所有版本都会缓存
                .into(mIv);
    }

    private void startLoadImage2() {
        ImageUtil.getBitmap(this, URL, new ImageUtil.OnBitmapLoad() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {
                Log.d("GlideImgActivity", "bright8#onBitmapReady");
                mIv.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed() {
                Log.d("GlideImgActivity", "bright8#onLoadFailed");
            }
        });
    }
}
