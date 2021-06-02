package com.xiaoming.androidpoints.layout_extend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

import androidx.fragment.app.FragmentActivity;

//公共标题栏：避免每次activity都得写标题栏：https://blog.csdn.net/u012885461/article/details/50476907
public abstract class LayoutBaseActivity extends FragmentActivity {
    private TextView leftTv, centerTv, rightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_base);

        leftTv = (TextView) findViewById(R.id.leftTv);
        centerTv = (TextView) findViewById(R.id.centerTv);
        rightTv = (TextView) findViewById(R.id.rightTv);
        if (getBackOnClickLisener() == null) {
            leftTv.setOnClickListener(new BackOnClickLisener());
        } else {
            leftTv.setOnClickListener(getBackOnClickLisener());
        }
        init();

    }

    public void setTitleAndContentLayoutId(String title, int layoutId) {
        getLayoutInflater().inflate(layoutId, (ViewGroup) centerTv.getParent().getParent());
        centerTv.setText(title);
    }

    public abstract void init();

    public abstract View.OnClickListener getBackOnClickLisener();

    class BackOnClickLisener implements View.OnClickListener {
        @Override
        public void onClick(View arg0) {
            finish();
        }
    }


}