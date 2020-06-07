package com.xiaoming.framelifecycle;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

//https://cloud.tencent.com/developer/article/1327880
public class MainActivity extends BaseAppActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goGank(View view) {
        startActivity(BeautyPicturesActivity.newIntent(this));
    }
}
