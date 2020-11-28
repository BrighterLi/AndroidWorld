package com.xiaoming.net.glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.net.R;

public class GlideTestEntranceActivity extends Activity implements View.OnClickListener {
    private Button mBtGlideImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test_entrance);

        mBtGlideImg = findViewById(R.id.bt_img);
        mBtGlideImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_img:
                startActivity(new Intent(GlideTestEntranceActivity.this, GlideImgActivity.class));
        }
    }
}
