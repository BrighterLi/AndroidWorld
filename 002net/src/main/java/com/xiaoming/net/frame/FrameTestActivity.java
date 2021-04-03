package com.xiaoming.net.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;
import com.xiaoming.net.frame.glide.GlideTestEntranceActivity;
import com.xiaoming.net.frame.rxjava.RxjavaTestActivity;

public class FrameTestActivity extends Activity implements View.OnClickListener {
    private Button mBtRxjava;
    private Button mBtGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_test);

        mBtRxjava = findViewById(R.id.bt_rxjava);
        mBtGlide = findViewById(R.id.bt_glide);
        mBtRxjava.setOnClickListener(this);
        mBtGlide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rxjava:
                startActivity(new Intent(FrameTestActivity.this, RxjavaTestActivity.class));
                break;
            case R.id.bt_glide:
                startActivity(new Intent(FrameTestActivity.this, GlideTestEntranceActivity.class));
                break;
        }
    }
}
