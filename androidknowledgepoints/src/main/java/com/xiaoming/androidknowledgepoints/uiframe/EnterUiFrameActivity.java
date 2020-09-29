package com.xiaoming.androidknowledgepoints.uiframe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.uiframe.ScrollView.UiFrame2Activity;
import com.xiaoming.androidknowledgepoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView.UiFrame3Activity;
import com.xiaoming.androidknowledgepoints.uiframe.recycleview.UiFrame1Activity;
import com.xiaoming.androidknowledgepoints.uiframe.waterfall.WaterFallActivity;

public class EnterUiFrameActivity extends Activity implements View.OnClickListener{
    private Button mBtRecyclerview;
    private Button mScrollview;
    private Button mScrollviewTablayoutFragment;
    private Button mBtWaterFall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ui_frame);

        initView();
    }

    private void initView() {
        mBtRecyclerview = findViewById(R.id.bt_recyclerview);
        mScrollview = findViewById(R.id.bt_scrollview);
        mScrollviewTablayoutFragment = findViewById(R.id.bt_scrollview_tablayout_fragment);
        mBtWaterFall = findViewById(R.id.bt_water_fall);

        mBtRecyclerview.setOnClickListener(this);
        mScrollview.setOnClickListener(this);
        mScrollviewTablayoutFragment.setOnClickListener(this);
        mBtWaterFall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recyclerview:
                startActivity(new Intent(EnterUiFrameActivity.this, UiFrame1Activity.class));
                break;
            case R.id.bt_scrollview:
                startActivity(new Intent(EnterUiFrameActivity.this, UiFrame2Activity.class));
                break;
            case R.id.bt_scrollview_tablayout_fragment:
                startActivity(new Intent(EnterUiFrameActivity.this, UiFrame3Activity.class));
                break;
            case R.id.bt_water_fall:
                startActivity(new Intent(EnterUiFrameActivity.this, WaterFallActivity.class));
                break;
        }
    }
}
