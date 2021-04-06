package com.widget.banner;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.widget.R;
import com.widget.banner.Banner.BannerActivity;
import android.widget.Button;

public class BannerTestActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_test);

        mBt1 = findViewById(R.id.button1);
        mBt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(BannerTestActivity.this, BannerActivity.class));
                break;

        }
    }
}
