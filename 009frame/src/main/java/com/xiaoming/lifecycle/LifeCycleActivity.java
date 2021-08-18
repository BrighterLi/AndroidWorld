package com.xiaoming.lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.xiaoming.framearouter.R;

//https://cloud.tencent.com/developer/article/1327880
public class LifeCycleActivity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }


    public void goGank(View view) {
        startActivity(BeautyPicturesActivity.newIntent(this));
    }
}
