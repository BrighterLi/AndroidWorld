package com.xiaoming.androidpoints.layout_extend;

import android.os.Bundle;
import android.view.View;

import com.xiaoming.androidpoints.R;

public class ExtendActivity extends LayoutBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);

    }

    @Override
    public void init() {
        setTitleAndContentLayoutId("首页", R.layout.activity_main);
    }

    @Override
    public View.OnClickListener getBackOnClickLisener() {
        return null;
    }

}