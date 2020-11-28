package com.xiaoming.net.glide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.xiaoming.net.R;

import java.net.URL;

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
                startLoadImage();
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
                .into(mIv);
    }
}
